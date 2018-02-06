/*
 * Copyright (c) 2016. Patrick Messina
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jobdetailsmanager.pocketemployment

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListAdapter
import org.joda.time.DateTime
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions
import java.util.*

@RuntimePermissions
class RecruiterCallsLogActivity : AppCompatActivity()
{

    lateinit var expListAdapter: ExpandableListAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recruiter_calls)

        //ButterKnife.bind(this);



        //RecruiterCallsLogActivityPermissionsDispatcher.queryCallsLogWithPermissionCheck(this)

    }

    @NeedsPermission(Manifest.permission.READ_CALL_LOG)
    internal fun queryCallsLog()
    {
        val callsLogContentProvider = CallsLogContentProvider(this)

        val recruiterContactDates = callsLogContentProvider.groupByDateCallsLog(callRows)
        /**
         * Todo: take recruiterContact Dates and requery to find dates today, this week, this month
         * and pipe to CallsFragment tabhost
         *
         */


        val recruiterContactKeys = ArrayList<String>()

        for (o in recruiterContactDates.keys.toTypedArray())
        {
            recruiterContactKeys.add(o.toString())
        }

        Collections.sort(recruiterContactKeys, Comparator { lhs, rhs ->
            val dt1 = DateTime(lhs)
            val dt2 = DateTime(rhs)

            if (dt1.isBefore(dt2)) return@Comparator -1
            if (dt1.isAfter(dt2)) 1 else 0
        })


        expListAdapter = RecruiterCallsExpListAdapter(this, recruiterContactKeys, recruiterContactDates)
        //        expListView.setAdapter(expListAdapter);
        //        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        //        {
        //            @Override
        //            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
        //            {
        //                Contact retrieveContact = (Contact)parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
        //
        //                Intent intent = new Intent(RecruiterCallsLogActivity.this, RecruiterCallsDetailsActivity.class);
        //                intent.putExtra("Contact", retrieveContact);
        //                startActivity(intent);
        //                return true;
        //            }
        //        });
    }

    @OnShowRationale(Manifest.permission.READ_CALL_LOG)
    internal fun ShowGetAccountsRationale(request: PermissionRequest)
    {
        ShowRationaleDialog("This app needs permission to fetch your calls log", request)
    }

    private fun ShowRationaleDialog(message: String, request: PermissionRequest)
    {
        AlertDialog.Builder(this).setPositiveButton("Allow") { dialog, which -> request.proceed() }.setNegativeButton("Deny") { dialog, which -> request.cancel() }.setCancelable(false).setMessage(message).show()
    }

    companion object
    {

        //    @BindView(R.id.expListView)
        //    ExpandableListView expListView;

        var callRows: List<CallRow>? = null
            internal set
    }
}

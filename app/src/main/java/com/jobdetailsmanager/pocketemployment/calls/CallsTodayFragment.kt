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

package com.jobdetailsmanager.pocketemployment.calls

import android.app.PendingIntent
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cursoradapter.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.jobdetailsmanager.pocketemployment.PocketEmploymentTodoList
import com.jobdetailsmanager.pocketemployment.R
import kotlinx.android.synthetic.main.fragment_lv_calls.*
import org.koin.core.KoinComponent
import org.koin.core.inject


@RequiresApi(Build.VERSION_CODES.O)
class CallsTodayFragment : Fragment(), KoinComponent, LoaderManager.LoaderCallbacks<Cursor>
{
    private val lcp: CallsLogContentProvider by inject()

    lateinit var cursorLoader: CursorLoader

    internal var listAdapter: CursorAdapter? = null

    lateinit var callsNotificationManager: CallsNotificationManager

//    override fun onCreate(savedInstanceState: Bundle?)
//    {
//        super.onCreate(savedInstanceState)
//
//        //callsNotificationManager = injector.kodein().value.with(activity).instance()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter = CallsCursorAdapter(requireActivity(), null)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_lv_calls, container, false)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        LoaderManager.getInstance(this).initLoader(0, null, this)

        //
        //        List<String> listDataHeader = new ArrayList<>();
        //        ArrayMap<String, List<Contact>> listDataChild = new ArrayMap<>();
        //
        //        List<DateTime> dateTimes = new ArrayList<>();
        //
        //        for (Contact contact : queryContacts)
        //        {
        //            dateTimes.add(new DateTime(contact.getDateCallReceived()));
        //        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>
    {
        cursorLoader = lcp.callsToday

        return cursorLoader

    }

    //Permission will be handled by calling Activity
    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor)
    {

        //        GreenDaoHelper helper = new GreenDaoHelper(getActivity());
        //        ContactDao contactDao = helper.initSession().getContactDao();
        //        List<Contact> queryContacts = contactDao.queryBuilder().build().list();
        //
        //TODO: Test
        //        cursor.moveToFirst();
        //        List<CallRow> callRows = new ArrayList<>();
        //        while(cursor.moveToNext())
        //        {
        //            DateTime dateTime = DateTime.parse(cursor.getString(cursor.getColumnIndex
        // (CallsLogContentProvider.CALLS_LOG_DATE)));
        //            String number = cursor.getString(cursor.getColumnIndex(CallsLogContentProvider
        // .CALLS_LOG_NUMBER));
        //            String name = cursor.getString(cursor.getColumnIndex(CallsLogContentProvider
        // .CALLS_LOG_CACHED_NAME));
        //            CallRow callRow = new CallRow();
        //            callRow.setCallName(name);
        //            callRow.setCallDate(dateTime);
        //            callRow.setPhoneNumber(number);
        //            callRows.add(callRow);
        //        }

        if (listAdapter!!.swapCursor(cursor) != null)
        {

            loader.commitContentChanged()

            Toast.makeText(activity, cursor.count.toString() + " Calls", Toast.LENGTH_SHORT).show()

            //TODO:Fix to use dependency injection
            //val callsNotificationManager = CallsNotificationManager.getInstance(activity)


            callsNotificationManager.setUpNotification(1, "Load finished, Phone Number added",
                    PendingIntent.getActivity(activity, 1, Intent(activity, PocketEmploymentTodoList::class.java), 0), false)

            Toast.makeText(activity, "Content Changed", Toast.LENGTH_LONG).show()

        }
        calls_listview.adapter = listAdapter

        ///CallsFragment.setUpListView(getActivity(), listView, cursor, adapter);
    }

    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        //val callsNotificationManager = CallsNotificationManager.getInstance(activity)

//        callsNotificationManager.setUpNotification(1, "Loader reset, Phone Number added",
//                PendingIntent.getActivity(activity, 1, Intent(activity, PocketEmploymentTodoList::class.java), 0), false)

        Toast.makeText(activity, "Content Reset", Toast.LENGTH_LONG).show()

        listAdapter!!.changeCursor(null)
    }



}

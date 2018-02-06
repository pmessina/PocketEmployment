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

import android.app.PendingIntent
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast

import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.FragmentInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with


class CallsTodayFragment : Fragment(), FragmentInjector,  LoaderManager.LoaderCallbacks<Cursor>
{
    override val injector: KodeinInjector = KodeinInjector()

    //@BindView(R.id.calls_listview)
    protected var listView: ListView? = null

    lateinit var lcp: CallsLogContentProvider

    lateinit var cursorLoader: CursorLoader

    internal var adapter: CursorAdapter? = null

    lateinit var callsNotificationManager: CallsNotificationManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initializeInjector()

        callsNotificationManager = injector.kodein().value.with(activity).instance()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.fragment_lv_calls, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        adapter = CallsCursorAdapter(activity, null)

        loaderManager.initLoader(0, null, this)


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

    override fun onCreateLoader(id: Int, args: Bundle): Loader<Cursor>
    {
        lcp = CallsLogContentProvider(activity)

        cursorLoader = lcp.getCallsToday()

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

        if (adapter!!.swapCursor(cursor) != null)
        {

            loader.commitContentChanged()

            Toast.makeText(activity, cursor.count.toString() + " Calls", Toast.LENGTH_SHORT).show()

            //TODO:Fix to use dependency injection
            //val callsNotificationManager = CallsNotificationManager.getInstance(activity)


            callsNotificationManager.setUpNotification(1, "Load finished, Phone Number added",
                    PendingIntent.getActivity(activity, 1, Intent(activity, PocketEmploymentTodoList::class.java), 0), false)

            Toast.makeText(activity, "Content Changed", Toast.LENGTH_LONG).show()

        }
        listView!!.adapter = adapter

        ///CallsFragment.setUpListView(getActivity(), listView, cursor, adapter);
    }

    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        //val callsNotificationManager = CallsNotificationManager.getInstance(activity)

        callsNotificationManager.setUpNotification(1, "Loader reset, Phone Number added",
                PendingIntent.getActivity(activity, 1, Intent(activity, PocketEmploymentTodoList::class.java), 0), false)

        Toast.makeText(activity, "Content Reset", Toast.LENGTH_LONG).show()
        if (adapter != null)
        {
            adapter!!.changeCursor(null)
        }
    }


    override fun onDestroy()
    {
        super.onDestroy()
        destroyInjector()
    }

}

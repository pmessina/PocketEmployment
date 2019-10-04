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

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.ListFragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InterviewsThisWeekFragment : ListFragment(), LoaderManager.LoaderCallbacks<Cursor>
{
    private var listAdapter: InterviewsCursorAdapter? = null
    //private val listView: ListView? = null
    private var gcp: AndroidCalendarProvider? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater!!.inflate(R.layout.fragment_lv_interviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        loaderManager.initLoader(2, null, this)

        //listView = (ListView)getActivity().findViewById(R.id.interviews_listview);
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>
    {
        gcp = AndroidCalendarProvider(activity!!)

        return gcp!!.eventsCurrentWeekCursorLoader
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor)
    {
        listAdapter = InterviewsCursorAdapter(activity!!, cursor)

        setListAdapter(listAdapter)
        //setUpListView(gcp, listView, cursor, listAdapter);
    }

    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        if (listAdapter != null)
        {
            listAdapter!!.changeCursor(null)
        }
    }

}

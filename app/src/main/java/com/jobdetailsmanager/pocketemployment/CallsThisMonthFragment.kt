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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class CallsThisMonthFragment : CallsFragment()
{
    private val listAdapter: InterviewsCursorAdapter? = null
    private var listView: ListView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater!!.inflate(R.layout.fragment_lv_calls, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        //getLoaderManager().initLoader(3, null, this);

        listView = activity.findViewById(R.id.calls_listview) as ListView
    }

    //    @Override
    //    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    //    {
    //        gcp = new AndroidCalendarProvider(getActivity());
    //
    //        return gcp.getEventsCurrentMonthCursorLoader();
    //    }
    //
    //    @Override
    //    public void onLoadFinished(Loader<Cursor> loader, final Cursor cursor)
    //    {
    //        listAdapter = new InterviewsCursorAdapter(getActivity(), cursor);
    //
    //        setUpListView(gcp, listView, cursor, listAdapter);
    //    }
    //
    //    @Override
    //    public void onLoaderReset(Loader<Cursor> loader)
    //    {
    //        if (listAdapter != null)
    //        {
    //            listAdapter.changeCursor(null);
    //        }
    //    }
}
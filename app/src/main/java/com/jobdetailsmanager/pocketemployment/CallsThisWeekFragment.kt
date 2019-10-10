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
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class CallsThisWeekFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>
{
    private var listAdapter: CallsCursorAdapter? = null

    //@BindView(R.id.calls_listview)
    internal var listView: ListView? = null

    private var lcp: CallsLogContentProvider? = null
    private var cursorLoader: CursorLoader? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_lv_calls, container, false)
        //ButterKnife.bind(this, view)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        listAdapter = CallsCursorAdapter(context!!, null)

        loaderManager.initLoader(2, null, this)

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>
    {
        lcp = CallsLogContentProvider(context!!)

        cursorLoader = lcp!!.callsThisWeek

        return cursorLoader!!
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor)
    {
        Toast.makeText(activity, cursor.count.toString() + " calls", Toast.LENGTH_LONG).show()
        listAdapter!!.swapCursor(cursor)
        listView!!.setAdapter(listAdapter)

    }

    //@OnItemClick(R.id.calls_listview)
    fun onClick(view: View)
    {
        Toast.makeText(activity, "Item Clicked", Toast.LENGTH_SHORT).show()
    }


    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        if (listAdapter != null)
        {
            listAdapter!!.changeCursor(null)
        }
    }


}

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

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.jobdetailsmanager.pocketemployment.R
import kotlinx.android.synthetic.main.fragment_lv_calls.*
import org.koin.core.KoinComponent
import org.koin.core.inject

@RequiresApi(Build.VERSION_CODES.O)
class CallsThisWeekFragment : Fragment(), KoinComponent, LoaderManager.LoaderCallbacks<Cursor> {
    private var listAdapter: CallsCursorAdapter? = null

    internal var listView: ListView? = null

    private val lcp: CallsLogContentProvider by inject()
    private var cursorLoader: CursorLoader? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lv_calls, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listAdapter = CallsCursorAdapter(requireContext(), null)

        LoaderManager.getInstance(this).initLoader(1, null, this)

    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        cursorLoader = lcp.callsThisWeek

        return cursorLoader!!
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor) {
        Toast.makeText(requireContext(), "${cursor.count} calls", Toast.LENGTH_LONG).show()
        listAdapter!!.swapCursor(cursor)
        calls_listview.adapter = listAdapter

    }

    //
//    //@OnItemClick(R.id.calls_listview)
//    fun onClick(view: View)
//    {
//        Toast.makeText(activity, "Item Clicked", Toast.LENGTH_SHORT).show()
//    }
//
//
    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (listAdapter != null) {
            listAdapter!!.changeCursor(null)
        }
    }


}

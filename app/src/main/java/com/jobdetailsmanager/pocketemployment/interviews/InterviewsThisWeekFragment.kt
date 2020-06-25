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

package com.jobdetailsmanager.pocketemployment.interviews

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.jobdetailsmanager.pocketemployment.AndroidCalendarProvider
import com.jobdetailsmanager.pocketemployment.R
import kotlinx.android.synthetic.main.fragment_lv_interviews.*
import org.koin.core.KoinComponent
import org.koin.core.inject

@RequiresApi(Build.VERSION_CODES.O)
class InterviewsThisWeekFragment : Fragment(), KoinComponent, LoaderManager.LoaderCallbacks<Cursor>
{
    private var listAdapter: InterviewsCursorAdapter? = null

    private val gcp: AndroidCalendarProvider? by inject()

    private val interviewsViewModel: InterviewsStateViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_lv_interviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        interviewsViewModel.interviewState.observe(viewLifecycleOwner, Observer { state -> render(state) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        LoaderManager.getInstance(this).initLoader(1, null, this)

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>
    {
        return gcp!!.eventsCurrentWeekCursorLoader
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor)
    {
        listAdapter = InterviewsCursorAdapter(requireActivity(), cursor)

        Toast.makeText(requireContext(), "Interviews This Week: ${cursor.count}", Toast.LENGTH_SHORT).show()

        interviewsViewModel.check(cursor.count)

        interviews_listview.adapter = listAdapter
        //setListAdapter(listAdapter)
    }

    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        Toast.makeText(requireContext(), "Refreshing Interviews This Week", Toast.LENGTH_SHORT).show()
        listAdapter?.notifyDataSetChanged()
    }

    private fun render(viewState: InterviewsState) {

        when (viewState){
            InterviewsState.NoInterviews->{
                interviewsVF.displayedChild = 0
            }
            InterviewsState.MultipleInterviews -> {
                interviewsVF.displayedChild = 1
            }
        }

    }
}

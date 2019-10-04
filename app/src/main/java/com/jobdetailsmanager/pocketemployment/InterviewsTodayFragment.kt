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
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.twotoasters.sectioncursoradapter.adapter.SectionCursorAdapter


class InterviewsTodayFragment : ListFragment(), LoaderManager.LoaderCallbacks<Cursor>
{
    private var listAdapter: SectionCursorAdapter<*, *, *>? = null
    private var gcp: AndroidCalendarProvider? = null

    private var cursorLoader: CursorLoader? = null

    //    @BindView(R.id.interviews_listview)
    //    protected ListView listView;


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        try
        {
            //listAdapter = new InterviewsCursorAdapter(getActivity(), null);
            listAdapter = CallsCursorAdapter(activity!!, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }

        loaderManager.initLoader(0, null, this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {


        return inflater!!.inflate(R.layout.fragment_lv_interviews, container, false)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>
    {
        gcp = AndroidCalendarProvider(activity!!)
        cursorLoader = gcp!!.eventsTodayCursorLoader
        return cursorLoader!!

    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor)
    {
        Toast.makeText(activity, "Updating interviews to: " + cursor.count, Toast.LENGTH_SHORT).show()
        //listAdapter = new InterviewsCursorAdapter(getActivity(), cursor, 0);

        //cursor.setNotificationUri(getActivity().getContentResolver(), );

        //        if (loader.takeContentChanged())
        //        {
        //            Toast.makeText(getActivity(), "Content Changed", Toast.LENGTH_LONG).show();
        //        }
        //        loader.commitContentChanged();

        listAdapter!!.swapCursor(cursor)

        this.setListAdapter(listAdapter)
        this.listView.onItemClickListener = null
        this.listView.setOnScrollListener(object : AbsListView.OnScrollListener
        {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int)
            {
                Toast.makeText(activity, "Scroll State Changed", Toast.LENGTH_SHORT).show()
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int)
            {
                Toast.makeText(activity, "onScroll", Toast.LENGTH_SHORT).show()
            }
        })

        //listView.setAdapter(listAdapter);

        //setUpListView(gcp, listView, cursor, listAdapter);
    }

    override fun onLoaderReset(loader: Loader<Cursor>)
    {
        if (listAdapter != null)
        {
            Toast.makeText(activity, "Updating interviews", Toast.LENGTH_SHORT).show()

            //listAdapter.changeCursor(null);

            //listAdapter.notifyDataSetChanged();

        }

    }

    fun setUpListView(gcp: AndroidCalendarProvider, listView: ListView, cursor: Cursor, listAdapter: InterviewsCursorAdapter)
    {


        //listAdapter.notifyDataSetChanged();


        //        LinearLayout lvLayout = (LinearLayout)getActivity().findViewById(R.id.interviews_layout);
        //        TextView textView = new TextView(getActivity());
        //
        //        if (cursor.getCount() == 0)
        //        {
        //            textView.setText("No Interviews: schedule an Interview");
        //            textView.setId(R.id.tvNoInterviews);
        //            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //        }

        if (cursor.count > 0)
        {
            //            if (getActivity().findViewById(R.id.tvNoInterviews) != null)
            //            {
            //                lvLayout.removeView(textView);
            //            }

            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                //                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //
                //                    Interview interview = gcp.fillInterviewFromCursor(cursor, listAdapter.getCursor().getPosition());
                //
                //                    EditInterviewsFragment editInterviewsFragment = new EditInterviewsFragment();
                //
                //                    if (interview != null)
                //                    {
                //                        Bundle args = new Bundle();
                //                        args.putSerializable("Interview", interview);
                //                        editInterviewsFragment.setArguments(args);
                //                    }
                //
                //                    fragmentTransaction.replace(R.id.drawer_layout_container, editInterviewsFragment);
                //                    fragmentTransaction.addToBackStack(null);
                //                    fragmentTransaction.commit();
            }
        }
        //        else
        //        {
        //            lvLayout.addView(textView, 0);
        //        }

        //        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        //        {
        //            @Override
        //            public void onScrollStateChanged(AbsListView view, int scrollState)
        //            {
        //                Toast.makeText(getActivity(), "Scroll State Changed", Toast.LENGTH_SHORT).show();
        //            }
        //
        //            @Override
        //            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
        //            {
        //
        //            }
        //        });
        val tvInterviewTitle = activity?.findViewById<View>(R.id.tvInterviewType) as TextView

        //if (tvInterviewTitle.getText())
    }

}

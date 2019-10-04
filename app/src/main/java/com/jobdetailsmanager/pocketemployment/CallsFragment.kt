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


import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTabHost
import androidx.cursoradapter.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView


open class CallsFragment : Fragment()
{
    //    @BindView(R.id.btnAddCall)
    //    Button button;

    //lateinit var fragmentManager: FragmentManager

    //@BindView(R.id.tabHost)
    internal var tabHost: FragmentTabHost? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.fragment_tab_host, container, false)
        //ButterKnife.bind(this, view)

        val intent = Intent(activity, RecruiterCallService::class.java)
        activity?.startService(intent)

        //childFragmentManager = this.getChildFragmentManager()

        tabHost!!.setup(activity!!, childFragmentManager, android.R.id.tabcontent)
        tabHost!!.addTab(tabHost!!.newTabSpec("today").setIndicator("Today"), CallsTodayFragment::class.java, null)
        tabHost!!.addTab(tabHost!!.newTabSpec("thisWeek").setIndicator("This Week"), CallsThisWeekFragment::class.java, null)
        tabHost!!.addTab(tabHost!!.newTabSpec("thisMonth").setIndicator("This Month"), CallsThisMonthFragment::class.java, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }

    //    @OnClick(R.id.btnAddCall)
    //    public void onClick(View view)
    //    {
    //
    //
    //        DialogFragment fragment = new JobContactDetailsFragment();
    //        getChildFragmentManager().beginTransaction().add(R.id.child_fragment, fragment).commit();
    //        //fragment.show(getFragmentManager(), "JobContactDetailsFragment");
    //
    //        //childFragmentManager.beginTransaction().add(R.id.add_calls_container, new JobContactDetailsFragment()).commit();
    //    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        //Set title of toolbar depending on drawer position
        val args = this.arguments
        if (args != null)
        {
            val drawerIndex = args.getInt("drawer_position")
            val drawerSelection = resources.getStringArray(R.array.drawer_items)[drawerIndex]
            activity?.title = drawerSelection
        }

    }

    companion object
    {


        fun setUpListView(activity: Activity, listView: ListView, cursor: Cursor, listAdapter: CursorAdapter)
        {
            listView.adapter = listAdapter

            //        LinearLayout lvLayout = (LinearLayout) activity.findViewById(R.id.calls_layout);
            //
            //        TextView textView = new TextView(activity); //(TextView)getActivity().findViewById(R.id.tvNoInterviews);
            //        textView.setText("No Calls: Add a Call");
            //        textView.setId(R.id.tvNoInterviews);
            //        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (cursor.count > 0)
            {
                //            if (activity.findViewById(R.id.tvNoInterviews) != null)
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

            //TextView tvInterviewTitle = (TextView)getActivity().findViewById(R.id.tvInterviewType);

            //if (tvInterviewTitle.getText())
        }
    }


}




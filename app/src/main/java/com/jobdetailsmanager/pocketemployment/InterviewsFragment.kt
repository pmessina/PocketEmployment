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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class InterviewsFragment : Fragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater!!.inflate(R.layout.fragment_tab_host, container, false)

        val childFragmentManager = this.childFragmentManager

        val tabHost = view.findViewById<View>(R.id.tabHost) as FragmentTabHost

        tabHost.setup(activity!!, childFragmentManager, android.R.id.tabcontent)
        tabHost.addTab(tabHost.newTabSpec("today").setIndicator("Today"), InterviewsTodayFragment::class.java, null)
        tabHost.addTab(tabHost.newTabSpec("thisWeek").setIndicator("This Week"), InterviewsThisWeekFragment::class.java, null)
        tabHost.addTab(tabHost.newTabSpec("thisMonth").setIndicator("This Month"), InterviewsThisMonthFragment::class.java, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }

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


}

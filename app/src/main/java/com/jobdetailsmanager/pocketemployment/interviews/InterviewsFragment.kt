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


import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jobdetailsmanager.pocketemployment.R
import kotlinx.android.synthetic.main.fragment_lv_interviews.*
import kotlinx.android.synthetic.main.fragment_tab_host.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions


@RequiresApi(Build.VERSION_CODES.O)
@RuntimePermissions
class InterviewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_tab_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getInterviewsWithPermissionCheck()

    }

    @NeedsPermission(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
    fun getInterviews(){
        val interviewsPagerAdapter = InterviewsFragmentStatePagerAdapter(childFragmentManager)
        callsViewPager.adapter = interviewsPagerAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

}

@RequiresApi(Build.VERSION_CODES.O)
class InterviewsFragmentStatePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InterviewsTodayFragment()
            1 -> InterviewsThisWeekFragment()
            2 -> InterviewsThisMonthFragment()
            else -> InterviewsFragment()
        }

    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Interviews Today"
            1 -> "Interviews This Week"
            2 -> "Interviews This Month"
            else -> "Interviews Tab"
        }

    }

}
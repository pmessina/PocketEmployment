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


import android.Manifest
import android.app.Activity
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.cursoradapter.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jobdetailsmanager.pocketemployment.R
import kotlinx.android.synthetic.main.fragment_tab_host.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class CallsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_host, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCallsVPWithPermissionCheck()


    }

    @NeedsPermission(Manifest.permission.READ_CALL_LOG)
    @RequiresApi(Build.VERSION_CODES.O)
    fun setUpCallsVP(){
        callsViewPager.adapter = CallsFragmentPagerAdapter(childFragmentManager)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
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


    companion object {


        fun setUpListView(activity: Activity, listView: ListView, cursor: Cursor, listAdapter: CursorAdapter) {
            listView.adapter = listAdapter

            //        LinearLayout lvLayout = (LinearLayout) activity.findViewById(R.id.calls_layout);
            //
            //        TextView textView = new TextView(activity); //(TextView)getActivity().findViewById(R.id.tvNoInterviews);
            //        textView.setText("No Calls: Add a Call");
            //        textView.setId(R.id.tvNoInterviews);
            //        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (cursor.count > 0) {
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

@RequiresApi(Build.VERSION_CODES.O)
class CallsFragmentPagerAdapter(val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> CallsTodayFragment()
            1 -> CallsThisWeekFragment()
            2 -> CallsThisMonthFragment()
            else -> CallsFragment()
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "Calls Today"
            1 -> "Calls this Week"
            2 -> "Calls this Month"
            else -> "Calls Today"
        }
    }
}




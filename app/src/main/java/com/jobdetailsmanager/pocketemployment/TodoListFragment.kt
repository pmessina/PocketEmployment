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
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance

import org.greenrobot.greendao.query.QueryBuilder

import java.util.ArrayList
import java.util.Arrays

import greendao.Contact
import greendao.ContactDao
import greendao.DaoSession
import greendao.Interview
import greendao.InterviewDao

class TodoListFragment : Fragment()
{
    private val kodein = LazyKodein(appKodein)

    val helper:GreenDaoHelper by kodein.instance()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        val args = this.arguments
        if (args != null)
        {
            val drawerIndex = args.getInt("drawer_position")
            val drawerSelection = resources.getStringArray(R.array.drawer_items)[drawerIndex]
            activity.title = drawerSelection
        }

        val numInterviews: Long = 0
        //List<Interview> queryAllInterviews = new ArrayList<>();

        //        try
        //        {


        val session = helper.initSession()


        val contacts = session.contactDao
        val queryContacts = contacts.queryBuilder().where(ContactDao.Properties.ContactFirstName.isNull, ContactDao.Properties.ContactLastName.isNull)

        val numberNullNames = queryContacts.count()

        val interviewDao = session.interviewDao

        val queryInterviews = interviewDao.queryBuilder()

        val numberInterviews = queryInterviews.count()

        val numInterviewsMessage = "You have $numberInterviews interviews"


        val numNamesToFill = "You have $numberNullNames numbers with unknown names"

        val numbersWithoutNames = ArrayList<String>()
        for (c in queryContacts.build().list())
        {
            numbersWithoutNames.add(c.contactPhoneNumber)
        }
        //
        //            numInterviews = interviewQuery.count();

        //        }
        //        catch(SQLException ex)
        //        {
        //            ex.printStackTrace();
        //            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        //        }

        //Populate Interviews listview
        val todoListView = activity.findViewById<View>(R.id.expListViewTodoList) as ExpandableListView


        todoListView.setOnChildClickListener(object : ExpandableListView.OnChildClickListener
        {
            internal var expandedPosition = -1
            override fun onChildClick(parent: ExpandableListView, v: View, groupPosition: Int, childPosition: Int, id: Long): Boolean
            {

                if (expandedPosition != groupPosition && expandedPosition != -1)
                {
                    todoListView.collapseGroup(expandedPosition)
                    expandedPosition = groupPosition
                }
                return false
            }
        })


        val todoListExpListAdapter = TodoListExpListAdapter(activity)
        val calls = resources.getStringArray(R.array.calls)
        val interviews = resources.getStringArray(R.array.interviews)
        val jobPositions = resources.getStringArray(R.array.job_positions)
        val staffingCompanies = resources.getStringArray(R.array.staffing_companies)
        val recruiters = resources.getStringArray(R.array.recruiters)

        todoListExpListAdapter.addListDataHeader(numNamesToFill)
        todoListExpListAdapter.addListDataChild(numNamesToFill, numbersWithoutNames)

        todoListExpListAdapter.addListDataHeader(numInterviewsMessage)
        todoListExpListAdapter.addListDataChild(interviews[0], Arrays.asList(interviews[1]))

        todoListExpListAdapter.addListDataHeader(staffingCompanies[0])
        todoListExpListAdapter.addListDataChild(staffingCompanies[0], Arrays.asList(staffingCompanies[1]))

        todoListExpListAdapter.addListDataHeader(jobPositions[0])
        todoListExpListAdapter.addListDataChild(jobPositions[0], Arrays.asList(jobPositions[1]))

        todoListExpListAdapter.addListDataHeader(recruiters[0])
        todoListExpListAdapter.addListDataChild(recruiters[0], Arrays.asList(recruiters[1]))

        todoListView.setAdapter(todoListExpListAdapter)

        todoListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val child = todoListExpListAdapter.getChild(groupPosition, childPosition) as String

            if (child === recruiters[1])
            {

                activity.supportFragmentManager.inTransaction {
                    replace(R.id.drawer_layout_container, JobContactDetailsFragment())
                }
                //PocketEmploymentTodoList.setFragment(new JobContactDetailsFragment());
            }

            false
        }

        //        Button addInterview = (Button)getActivity().findViewById(R.id.todo_add_interview);
        //
        //        addInterview.setOnClickListener(new View.OnClickListener()
        //        {
        //            @Override
        //            public void onClick(View v)
        //            {
        //                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //                fragmentTransaction.replace(R.id.drawer_layout_container, new EditInterviewsFragment());
        //                fragmentTransaction.addToBackStack(null);
        //                fragmentTransaction.commit();
        //            }
        //        });
    }
}

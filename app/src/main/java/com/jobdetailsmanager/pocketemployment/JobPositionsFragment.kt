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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast


//TODO: Call Dice APIs
class JobPositionsFragment : Fragment(), JobPositionsDialogFragment.AddJobPositionsListener
{
    //@BindView(R.id.lvJobPositions)
    internal var lvJobPositions: ListView? = null

    //@OnClick(R.id.fabAddJobPosition)
    fun onButtonClick(view: View)
    {
        val fragment = JobPositionsDialogFragment()

        fragment.show(fragmentManager!!, "jobPositionsDialogFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val args = this.arguments

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_job_positions, container, false)

        val args = this.arguments
        if (args != null)
        {
            val drawerIndex = args.getInt("drawer_position")
            val drawerSelection = resources.getStringArray(R.array.drawer_items)[drawerIndex]
            activity?.title = drawerSelection

            val output = args.getString("data")
            //List<String> data = Arrays.asList(args.getString("data"));

            val greenDaoHelper = GreenDaoHelper(activity!!)

            //val jobTitles = greenDaoHelper.initSession().jobPositionDao.queryBuilder().list()

            //val adapter = JobPositionsListAdapter(activity!!, android.R.layout.simple_list_item_1, jobTitles)
            //lvJobPositions!!.adapter = adapter

            lvJobPositions!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
                {
                    Toast.makeText(activity, position.toString() + " selected", Toast.LENGTH_LONG).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>)
                {

                }
            }

        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        val args = this.arguments
    }

    override fun sendData(data: String)
    {
        Toast.makeText(activity, data, Toast.LENGTH_LONG).show()
    }

}

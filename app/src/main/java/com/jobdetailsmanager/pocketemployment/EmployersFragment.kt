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

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class EmployersFragment : ListFragment()
{

    //lateinit var employerArrayAdapter: ArrayAdapter<Employer>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val args = this.arguments
        if (args != null)
        {
            val drawerIndex = args.getInt("drawer_position")
            val drawerSelection = resources.getStringArray(R.array.drawer_items)[drawerIndex]
            activity?.title = drawerSelection
        }

        return inflater!!.inflate(R.layout.fragment_employers, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        //InitData(getActivity());
        //this.registerForContextMenu(getListView());
        super.onActivityCreated(savedInstanceState)

        listAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, this.arguments?.getStringArrayList("messages")!!)

    }

    private fun initData(context: Context)
    {
        //        try
        //        {

        val employerListView = this.listView

        //val employerList = helper.initSession().employerDao.queryBuilder().build().list()

//        employerArrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, employerList)
//
//        if (employerArrayAdapter.isEmpty)
//        {
//            ToEditEmployersFragment()
//        }

        //employerListView.adapter = employerArrayAdapter

        employerListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> ToEditEmployersFragment() }

        //        }
        //        catch (SQLException e)
        //        {
        //            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        //        }
    }

    fun ToEditEmployersFragment()
    {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        //fragmentTransaction?.replace(R.id.drawer_layout_container, EditEmployersFragment())
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu, v, menuInfo)

        val menuInflater = activity?.menuInflater
        menuInflater?.inflate(R.menu.menu_employers, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {

//        val cmi = item!!.menuInfo as AdapterView.AdapterContextMenuInfo
//        val index = cmi.position
//
//        val employer = employerArrayAdapter.getItem(index)
//
//        if (employerArrayAdapter.isEmpty)
//        {
//            ToEditEmployersFragment()
//        } else
//        {
//
//            //            final DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
//
//            val dialog = AlertDialog.Builder(activity).setTitle("Delete Employer").setMessage("Are you sure you want to delete $employer?").setPositiveButton("Yes") { dialog, which ->
//                        //                            try
//                        //                            {
//                        val helper = GreenDaoHelper(activity!!)
//
//                        val employerDao = helper.initSession().employerDao
//
//                        employerDao.delete(employer)
//
//                        employerArrayAdapter.remove(employer)
//
//                        Toast.makeText(activity, "Employer " + employer!!.employerName + " was deleted", Toast.LENGTH_LONG).show()
//
//                        //                            }
//                        //                            catch (SQLException e)
//                        //                            {
//                        //                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
//                        //                            }
//                    }.setNegativeButton("No") { dialog, which ->
//                        //isRecruiter = false;
//                    }
//            val ad = dialog.create()
//            ad.show()
//        }

        return super.onContextItemSelected(item)
    }
}

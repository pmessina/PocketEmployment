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
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast

import java.util.ArrayList


class StaffingCompaniesFragment : Fragment()
{

    //@BindView(R.id.edtStaffingCompanyName)
    internal var edtStaffingCompanyName: EditText? = null

    //@BindView(R.id.imgbtnAddStaffingCompany)
    internal var imageButton: ImageButton? = null

    //@BindView(R.id.lvStaffingCompanies)
    internal var listView: ListView? = null

    lateinit var arrayAdapter: ArrayAdapter<String>

    lateinit var stringList: MutableList<String>

    lateinit var greenDaoHelper: GreenDaoHelper

    lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        return inflater!!.inflate(R.layout.fragment_staffing_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        //arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        arrayAdapter = StaffingCompaniesListAdapter(activity!!, android.R.layout.simple_list_item_1)
        stringList = ArrayList()

        greenDaoHelper = GreenDaoHelper(activity!!)
        //val recruiterCompanyDao = greenDaoHelper.initSession().recruiterCompanyDao

//        val recruiterCompanies = recruiterCompanyDao.queryBuilder().list()
//
//        //TODO: change adapter to add RecruiterCompany instead of String
//        for (recruiterCompany in recruiterCompanies)
//        {
//            arrayAdapter.add(recruiterCompany.recruiterCompanyName)
//        }

        listView!!.adapter = arrayAdapter

        //greenDaoHelper.closeSession()

    }

    //@OnClick(R.id.imgbtnAddStaffingCompany)
    fun addStaff(view: View)
    {
        val text = edtStaffingCompanyName!!.text.toString()

        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        if (!text.isEmpty())
        {
            stringList.add(text)
            arrayAdapter.add(text)

            arrayAdapter.notifyDataSetChanged()
            listView!!.adapter = arrayAdapter
            edtStaffingCompanyName!!.text.clear()

            try
            {
                //val recruiterCompanyDao = greenDaoHelper.initSession().recruiterCompanyDao
//                val recruiterCompany = RecruiterCompany()
//                recruiterCompany.recruiterCompanyName = text
//                val insertSuccessful = recruiterCompanyDao.insert(recruiterCompany)
//
//                if (insertSuccessful > -1)
//                {
//                    Toast.makeText(activity, "Recruiter Company added successfully", Toast.LENGTH_SHORT).show()
//                } else
//                {
//                    Toast.makeText(activity, "Failed to insert recruiting company", Toast.LENGTH_SHORT).show()
//                }


                //greenDaoHelper.closeSession()
            }
            catch (ex: Exception)
            {
                Toast.makeText(activity, ex.message, Toast.LENGTH_SHORT).show()
            }

        } else
        {
            Toast.makeText(activity, "Please enter some text", Toast.LENGTH_SHORT).show()
        }
    }


}

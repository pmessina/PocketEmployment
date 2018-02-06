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


import android.content.Context
import android.database.DataSetObserver
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageButton
import android.widget.TextView

import java.util.ArrayList
import java.util.HashMap

import greendao.Interview

class InterviewsExpListAdapter : BaseExpandableListAdapter
{
    private var context: Context? = null
    private var listDataHeader: MutableList<String>? = null
    private var listDataChild: HashMap<String, MutableList<Interview>>? = null

    private val lastExpandedPosition = -1

    constructor(context: Context, listDataHeader: MutableList<String>, listDataChild: HashMap<String, MutableList<Interview>>)
    {
        this.context = context
        this.listDataHeader = listDataHeader
        this.listDataChild = listDataChild
    }

    constructor(context: Context)
    {
        this.context = context
    }

    fun addListDataHeader(dataHeader: String)
    {
        if (listDataHeader == null)
        {
            listDataHeader = ArrayList()
        }
        listDataHeader!!.add(dataHeader)
    }


    fun addListDataChild(header: String, dataChild: MutableList<Interview>)
    {
        if (listDataChild == null)
        {
            listDataChild = HashMap()
        }

        listDataChild!![header] = dataChild
    }

    fun deleteChild(groupPosition: Int, childPosition: Int)
    {
        val ldh = listDataHeader!![groupPosition]
        val ldc = listDataChild!![ldh]
        ldc!!.removeAt(childPosition)
    }

    fun deleteAll()
    {
        listDataChild = null
        listDataHeader = null
    }


    override fun registerDataSetObserver(observer: DataSetObserver)
    {
        super.registerDataSetObserver(observer)
    }

    override fun notifyDataSetChanged()
    {
        super.notifyDataSetChanged()
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any
    {
        val ldh = listDataHeader!![groupPosition]
        val ldc = listDataChild!![ldh]
        return ldc!!.get(childPosition)
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long
    {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View
    {
        var convertView = convertView
        val interview = getChild(groupPosition, childPosition) as Interview

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.interviews_explv_row_layout, null)
        }

        val interviewType = convertView!!.findViewById<View>(R.id.tvInterviewType) as TextView
        val timeStart = convertView.findViewById<View>(R.id.tvStartTime) as TextView
        val timeEnd = convertView.findViewById<View>(R.id.tvEndTime) as TextView
        val delInterview = convertView.findViewById<View>(R.id.btnDeleteInterview) as ImageButton

        if (interview != null)
        {
            delInterview.setOnClickListener {
                //                    try
                //                    {
                //DatabaseHelper helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
                val helper = GreenDaoHelper(context!!)
                helper.initSession().interviewDao.delete(interview)
                deleteChild(groupPosition, childPosition)
                notifyDataSetChanged()

                //                        if (helper.getInterviewDao().delete(interview) > 0)
                //                        {
                //                            Toast.makeText(context, interview.getInterviewType() + " deleted", Toast.LENGTH_LONG).show();
                //
                //                        }

                //                    }
                //                    catch (SQLException ex)
                //                    {
                //                        ex.printStackTrace();
                //                        Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                //                    }
            }

            if (interviewType != null && timeStart != null && timeEnd != null)
            {
                timeStart.text = interview.startTimeString
                timeEnd.text = interview.endTimeString
                interviewType.text = interview.interviewType
            }
        }

        return convertView
    }


    override fun getChildrenCount(groupPosition: Int): Int
    {
        val ldh = listDataHeader!![groupPosition]
        val ldc = listDataChild!![ldh]

        return ldc!!.size
    }

    override fun getGroup(groupPosition: Int): Any
    {
        return listDataHeader!![groupPosition]
    }

    override fun getGroupCount(): Int
    {
        return listDataHeader!!.size
    }

    override fun getGroupId(groupPosition: Int): Long
    {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View
    {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.interviews_date_tv_separator, null)
        }

        //String goes here instead of date
        val lblListHeader = convertView!!.findViewById<View>(R.id.tvInterviewDate) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD_ITALIC)
        lblListHeader.text = headerTitle

        //        ImageButton imageButton = (ImageButton)convertView.findViewById(R.id.imgAddInterview);
        //
        //        imageButton.setOnClickListener(new View.OnClickListener()
        //        {
        //            @Override
        //            public void onClick(View v)
        //            {
        //                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        //
        //                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //                fragmentTransaction.replace(R.id.drawer_layout_container, new EditInterviewsFragment());
        //                fragmentTransaction.addToBackStack(null);
        //                fragmentTransaction.commit();
        //            }
        //        });

        return convertView
    }

    override fun hasStableIds(): Boolean
    {
        return false
    }

    override fun isChildSelectable(arg0: Int, arg1: Int): Boolean
    {
        return true
    }

}

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
import android.support.v4.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import greendao.Contact

class RecruiterCallsExpListAdapter<T : Contact>(private val context: Context, private var listDataHeader: MutableList<String>, private var listDataChild: ArrayMap<String, MutableList<T>>) : BaseExpandableListAdapter()
{

    private val lastExpandedPosition = -1


    //    public RecruiterCallsExpListAdapter(Context context, List<String> listDataHeader, ArrayMap<String, List<Recruiter>> listDataChild)
    //    {
    //        this.context = context;
    //        this.listDataHeader = listDataHeader;
    //        this.listDataChild = listDataChild;
    //    }

    fun deleteChild(groupPosition: Int, childPosition: Int)
    {
        val ldh = listDataHeader[groupPosition]
        val ldc = listDataChild[ldh]
        ldc!!.removeAt(childPosition)
    }

    fun deleteAll()
    {
        listDataChild.clear()
        listDataHeader.clear()
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

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View?
    {
        var view = convertView

        val contact = getChild(groupPosition, childPosition) as T

        val formattedPhoneNumber = contact.formattedRecruiterPhoneNumber
        val contactFirstName = contact.contactFirstName
        val callsLogTime = contact.timeCallReceived

        if (view == null)
        {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.recruiter_contact_info, null)
        }

        if (formattedPhoneNumber != null)
        {
            val textListChildPhoneNum = view!!.findViewById<View>(R.id.tvPhoneNumber) as TextView

            textListChildPhoneNum.text = formattedPhoneNumber
        }

        if (contactFirstName != null)
        {
            val textListChildName = view!!.findViewById<View>(R.id.tvName) as TextView
            textListChildName.text = contact.contactFirstName
        }

        if (callsLogTime != null)
        {
            val textListChildTime = view!!.findViewById<View>(R.id.tvTime) as TextView
            textListChildTime.text = contact.timeCallReceived
        }


        return view
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
        val formatter = DateTimeFormat.fullDate()
        val formattedHeaderTitle = formatter.print(DateTime(headerTitle))

        if (convertView == null)
        {
            val infalInflater = LayoutInflater.from(context)
            convertView = infalInflater.inflate(R.layout.recruiter_contact_info_group, null)
        }

        val lblListHeader = convertView!!.findViewById<View>(R.id.tvDate) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD_ITALIC)
        lblListHeader.text = formattedHeaderTitle

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

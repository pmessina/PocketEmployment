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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.*


class TodoListExpListAdapter(val context: Context) : BaseExpandableListAdapter()// var listDataHeader: MutableList<String>, var listDataChild: TreeMap<String, MutableList<String>>)
{
    //private var context: Context? = null
    lateinit var listDataHeader: MutableList<String>
    lateinit var listDataChild: TreeMap<String, MutableList<String>>

    private val lastExpandedPosition = -1

    fun addListDataHeader(dataHeader: String)
    {
        if (listDataHeader == null)
        {
            listDataHeader = ArrayList()
        }
        listDataHeader.add(dataHeader)
    }


    fun addListDataChild(header: String, dataChild: MutableList<String>)
    {
        this.listDataChild[header] = dataChild
    }

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
        val ldh = listDataHeader[groupPosition]
        val ldc = listDataChild[ldh]
        return ldc!!.get(childPosition)
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long
    {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, viewGroup: ViewGroup): View
    {
        var convertView = convertView
        val child = getChild(groupPosition, childPosition) as String

        val textViewHolder: ChildViewHolder

        if (convertView != null)
        {
            textViewHolder = convertView.tag as ChildViewHolder
        } else
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.todolist_child_layout, viewGroup, false)
            textViewHolder = ChildViewHolder(convertView)
            convertView!!.tag = textViewHolder
        }

        textViewHolder.todoListChild!!.text = child

        textViewHolder.todoListChild!!.setOnClickListener {
            when (groupPosition)
            {
                0 -> {}//RecruiterCallsTodoList.setFragment(JobContactDetailsFragment())
                1 -> {}//RecruiterCallsTodoList.setFragment(InterviewsFragment())
                2 -> {}//RecruiterCallsTodoList.setFragment(StaffingCompaniesFragment())
                3 -> {}//RecruiterCallsTodoList.setFragment(JobPositionsFragment())
                4 -> {}//RecruiterCallsTodoList.setFragment(JobContactDetailsFragment())
                else ->
                {
                }
            }//context.startActivity(new Intent(context, CallsActivity.class));
        }


        return convertView
    }


    override fun getChildrenCount(groupPosition: Int): Int
    {
        val ldh = listDataHeader!![groupPosition]
        val ldc = listDataChild!![ldh]

        return ldc?.size ?: 0

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

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, viewGroup: ViewGroup): View
    {
        var view = convertView
        val parent = getGroup(groupPosition) as String

        val textViewHolder: ParentViewHolder

        if (view != null)
        {
            textViewHolder = view.tag as ParentViewHolder
        } else
        {
            view = LayoutInflater.from(context).inflate(R.layout.todolist_parent_layout, viewGroup, false)
            textViewHolder = ParentViewHolder(view)
            view!!.tag = textViewHolder
        }

        textViewHolder.todoListParent!!.text = parent

        return view
    }

    override fun hasStableIds(): Boolean
    {
        return false
    }

    override fun isChildSelectable(arg0: Int, arg1: Int): Boolean
    {
        return true
    }

    internal class ParentViewHolder(view: View)
    {
        //@BindView(R.id.tvParentTodoList)
        var todoListParent: TextView? = null

        init
        {

        }
    }

    internal class ChildViewHolder(view: View)
    {
        //@BindView(R.id.tvChildTodoList)
        var todoListChild: TextView? = null

        init
        {

        }
    }

}



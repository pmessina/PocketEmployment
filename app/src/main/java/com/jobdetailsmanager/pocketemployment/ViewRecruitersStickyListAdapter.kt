package com.jobdetailsmanager.pocketemployment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jobdetailsmanager.pocketemployment.database.Recruiter
import com.jobdetailsmanager.pocketemployment.database.RecruiterCompany

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * Created by Patrick on 1/13/2017.
 */

class ViewRecruitersStickyListAdapter(private var context: Context, private var recruiters: List<Recruiter>) : BaseAdapter(), StickyListHeadersAdapter {
    override fun getCount(): Int {
        return recruiters.size
    }

    override fun getItem(position: Int): Recruiter {
        return recruiters[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.todolist_child_layout, parent, false)

        val todoListChild = view.findViewById(R.id.tvChildTodoList) as TextView
        //view.tag = todoListChild as TextView
        todoListChild.text = getItem(position).recruiterPhoneNumber


        return view
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.todolist_parent_layout, parent, false)
        var todoListParent = view.findViewById(R.id.tvParentTodoList) as TextView

        val headerViewItem = getItem(position)

        todoListParent?.text = String.format("%s %s", headerViewItem.recruiterEmailAdress, headerViewItem.recruiterCompanyId)

        return view
    }

    override fun getHeaderId(position: Int): Long {
        return position.toLong()
    }

}

class ViewRecruitingCompaniesStickyListAdapter(private var context: Context, private var companies: List<RecruiterCompany>) : BaseAdapter(), StickyListHeadersAdapter {
    override fun getCount(): Int {
        return companies.size
    }

    override fun getItem(position: Int): RecruiterCompany {
        return companies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.todolist_child_layout, parent, false)

        val todoListChild = view.findViewById(R.id.tvChildTodoList) as TextView
        //view.tag = todoListChild as TextView
        todoListChild.text = getItem(position).recruiterCompanyWebsite
        return view
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.todolist_parent_layout, parent, false)
        var todoListParent = view.findViewById(R.id.tvParentTodoList) as TextView

        val headerViewItem = getItem(position)

        todoListParent?.text = String.format("%s %s", headerViewItem.recruiterCompanyAddress, headerViewItem.recruiterCompanyId)

        return view
    }

    override fun getHeaderId(position: Int): Long {
        return position.toLong()
    }

}

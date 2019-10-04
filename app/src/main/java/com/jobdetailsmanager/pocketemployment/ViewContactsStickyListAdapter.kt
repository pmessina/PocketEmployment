package com.jobdetailsmanager.pocketemployment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * Created by Patrick on 1/13/2017.
 */

//class ViewContactsStickyListAdapter<T : Contact>(internal var context: Context, internal var contacts: List<T>) : BaseAdapter(), StickyListHeadersAdapter
//{
//    override fun getCount(): Int
//    {
//        return contacts.size
//    }
//
//    override fun getItem(position: Int): T
//    {
//        return contacts[position]
//    }
//
//    override fun getItemId(position: Int): Long
//    {
//        return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
//    {
//        var view = convertView
//        val childViewHolder: ChildViewHolder
//        if (view == null)
//        {
//            view = LayoutInflater.from(context).inflate(R.layout.todolist_child_layout, parent, false)
//            childViewHolder = ChildViewHolder(view)
//            childViewHolder.todoListChild = view!!.findViewById(R.id.tvChildTodoList) as TextView
//            view.tag = childViewHolder
//        } else
//        {
//            childViewHolder = view.tag as ChildViewHolder
//        }
//
//        childViewHolder.todoListChild!!.text = getItem(position).contactPhoneNumber
//
//        return view
//    }
//
//    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup): View
//    {
//        var convertView = convertView
//        val parentViewHolder: ParentViewHolder
//        if (convertView == null)
//        {
//            convertView = LayoutInflater.from(context).inflate(R.layout.todolist_parent_layout, parent, false)
//            parentViewHolder = ParentViewHolder(convertView)
//            parentViewHolder.todoListParent = convertView!!.findViewById(R.id.tvParentTodoList) as TextView
//            convertView.tag = parentViewHolder
//        } else
//        {
//            parentViewHolder = convertView.tag as ParentViewHolder
//        }
//
//        parentViewHolder.todoListParent!!.text = getItem(position).contactFirstName
//
//        return convertView
//    }
//
//    override fun getHeaderId(position: Int): Long
//    {
//        return position.toLong()
//    }
//
//    internal class ParentViewHolder(view: View)
//    {
//        //@BindView(R.id.tvParentTodoList)
//        var todoListParent: TextView? = null
//
//        init
//        {
//            //ButterKnife.bind(this, view)
//        }
//    }
//
//    internal class ChildViewHolder(view: View)
//    {
//        //@BindView(R.id.tvChildTodoList)
//        var todoListChild: TextView? = null
//
//        init
//        {
//            //ButterKnife.bind(this, view)
//        }
//    }
//}

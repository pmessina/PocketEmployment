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

package com.jobdetailsmanager.pocketemployment.recruiters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.collection.ArrayMap
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.jobdetailsmanager.pocketemployment.R
import com.jobdetailsmanager.pocketemployment.database.Contact
import com.jobdetailsmanager.pocketemployment.database.Recruiter
import com.jobdetailsmanager.pocketemployment.database.RecruiterCompany

class RecruiterCallsExpListAdapter(private val context: Context, private var listDataHeader: MutableList<String>, private var listDataChild: ArrayMap<String, MutableList<Contact>>) : ExpandableRecyclerAdapter<RecruiterCompanyViewHolder, RecruiterViewHolder>(context, emptyList()) {


//    private val lastExpandedPosition = -1
//
//
//    //    public RecruiterCallsExpListAdapter(Context context, List<String> listDataHeader, ArrayMap<String, List<Recruiter>> listDataChild)
//    //    {
//    //        this.context = context;
//    //        this.listDataHeader = listDataHeader;
//    //        this.listDataChild = listDataChild;
//    //    }
//
//    fun deleteChild(groupPosition: Int, childPosition: Int) {
//        val ldh = listDataHeader[groupPosition]
//        val ldc = listDataChild[ldh]
//        ldc!!.removeAt(childPosition)
//    }
//
//    fun deleteAll() {
//        listDataChild.clear()
//        listDataHeader.clear()
//    }
//
//
//    override fun registerDataSetObserver(observer: DataSetObserver) {
//        super.registerDataSetObserver(observer)
//    }
//
//    override fun notifyDataSetChanged() {
//        super.notifyDataSetChanged()
//    }
//
//    override fun getChild(groupPosition: Int, childPosition: Int): Any {
//        val ldh = listDataHeader!![groupPosition]
//        val ldc = listDataChild!![ldh]
//        return ldc!!.get(childPosition)
//    }
//
//    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
//        return childPosition.toLong()
//    }
//
//    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View? {
//        var view = convertView
//
//        val contact = getChild(groupPosition, childPosition) as Contact
//
//        val formattedPhoneNumber = contact.formattedRecruiterPhoneNumber
//        val contactFirstName = contact.contactFirstName
//        val callsLogTime = contact.timeCallReceived
//
//        if (view == null) {
//            val inflater = LayoutInflater.from(context)
//            view = inflater.inflate(R.layout.recruiter_contact_info, null)
//        }
//
//        if (formattedPhoneNumber != null) {
//            val textListChildPhoneNum = view!!.findViewById<View>(R.id.tvPhoneNumber) as TextView
//
//            textListChildPhoneNum.text = formattedPhoneNumber
//        }
//
//        if (contactFirstName != null) {
//            val textListChildName = view!!.findViewById<View>(R.id.tvName) as TextView
//            textListChildName.text = contact.contactFirstName
//        }
//
//        if (callsLogTime != null) {
//            val textListChildTime = view!!.findViewById<View>(R.id.tvTime) as TextView
//            textListChildTime.text = contact.timeCallReceived
//        }
//
//
//        return view
//    }
//
//
//    override fun getChildrenCount(groupPosition: Int): Int {
//        val ldh = listDataHeader!![groupPosition]
//        val ldc = listDataChild!![ldh]
//
//        return ldc!!.size
//    }
//
//    override fun getGroup(groupPosition: Int): Any {
//        return listDataHeader!![groupPosition]
//    }
//
//    override fun getGroupCount(): Int {
//        return listDataHeader!!.size
//    }
//
//    override fun getGroupId(groupPosition: Int): Long {
//        return groupPosition.toLong()
//    }
//
//    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//        val headerTitle = getGroup(groupPosition) as String
//        val formatter = DateTimeFormat.fullDate()
//        val formattedHeaderTitle = formatter.print(DateTime(headerTitle))
//
//        if (convertView == null) {
//            val infalInflater = LayoutInflater.from(context)
//            convertView = infalInflater.inflate(R.layout.recruiter_contact_info_date, null)
//        }
//
//        val lblListHeader = convertView!!.findViewById<View>(R.id.tvDate) as TextView
//        lblListHeader.setTypeface(null, Typeface.BOLD_ITALIC)
//        lblListHeader.text = formattedHeaderTitle
//
//        return convertView
//    }
//
//    override fun hasStableIds(): Boolean {
//        return false
//    }
//
//    override fun isChildSelectable(arg0: Int, arg1: Int): Boolean {
//        return true
//    }

    override fun onBindParentViewHolder(recruiterCompanyViewHolder: RecruiterCompanyViewHolder, p1: Int, item: Any?) {
        val recruiterCompany = item as RecruiterCompany

        recruiterCompanyViewHolder.apply {
            tvRecruiterCompanyEmail.text = recruiterCompany.recruiterCompanyAddress
            tvRecruiterCompanyName.text = recruiterCompany.recruiterCompanyName
            tvRecruiterCompanyWebsite.text = recruiterCompany.recruiterCompanyWebsite
        }
    }

    override fun onCreateChildViewHolder(p0: ViewGroup?): RecruiterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recruiter_view, p0, false)
        return RecruiterViewHolder(view)
    }

    override fun onCreateParentViewHolder(p0: ViewGroup?): RecruiterCompanyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recruiter_company_view, p0, false)
        return RecruiterCompanyViewHolder(view)
    }

    override fun onBindChildViewHolder(recruiterViewHolder: RecruiterViewHolder, p1: Int, item: Any?) {
        val recruiter = item as Recruiter

        recruiterViewHolder.apply {
            tvRecruiterEmailAddress.text = recruiter.recruiterEmailAdress
            tvRecruiterEmailAddress.text = recruiter.recruiterName
            tvRecruiterPhoneNumber.text = recruiter.recruiterPhoneNumber
        }
    }


}

//recruiter_company_view
class RecruiterCompanyViewHolder(v: View) : ParentViewHolder(v) {
    val tvRecruiterCompanyName = v.findViewById(R.id.tvRecruiterCompanyName) as TextView
    val tvRecruiterCompanyEmail = v.findViewById(R.id.tvRecruiterCompanyAddress) as TextView
    val tvRecruiterCompanyWebsite = v.findViewById(R.id.tvRecruiterCompanyWebsite) as TextView
}

//recruiter_view
class RecruiterViewHolder(v: View) : ChildViewHolder(v) {
    val tvRecruiterName = v.findViewById(R.id.tvRecruiterName) as TextView
    val tvRecruiterEmailAddress = v.findViewById(R.id.tvRecruiterEmailAddress) as TextView
    val tvRecruiterPhoneNumber = v.findViewById(R.id.tvRecruiterPhoneNumber) as TextView
}

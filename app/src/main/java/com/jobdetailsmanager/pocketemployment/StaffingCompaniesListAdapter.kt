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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton

import greendao.RecruiterCompany
import greendao.RecruiterCompanyDao

class StaffingCompaniesListAdapter : ArrayAdapter<String>
{

    lateinit var values: List<String>

    constructor(context: Context, resource: Int, values: List<String>) : super(context, resource, values)
    {
        this.values = values
    }

    constructor(context: Context, resource: Int) : super(context, resource)
    {

    }


    fun remove(location: Int)
    {
        val greenDaoHelper = GreenDaoHelper(context!!)
        val recruiterCompanyDao = greenDaoHelper.initSession().recruiterCompanyDao


        val recruiterCompanies = recruiterCompanyDao.queryBuilder().where(RecruiterCompanyDao.Properties.RecruiterCompanyName.eq(getItem(location))).build().list()
        recruiterCompanyDao.delete(recruiterCompanies[0])


        super.remove(getItem(location))
        this.notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var convertView = convertView
        val viewHolder: ViewHolder

        val inflater = LayoutInflater.from(context)

        if (convertView != null)
        {
            viewHolder = convertView.tag as ViewHolder
        } else
        {
            convertView = inflater.inflate(R.layout.fragment_staffing_company_info, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView!!.tag = viewHolder
        }

        viewHolder.tvStaffingCompany!!.setText(getItem(position))

        viewHolder.imageButtonRemoveStaffingCompany!!.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setMessage("Delete Staffing Company?").setPositiveButton("Yes") { dialog, which -> remove(position) }.setNegativeButton("No") { dialog, which -> dialog.dismiss() }.show()
        }

        return convertView
    }

    internal class ViewHolder(view: View)
    {
        var tvStaffingCompany: EditText? = null

        var imageButtonRemoveStaffingCompany: ImageButton? = null
    }


}

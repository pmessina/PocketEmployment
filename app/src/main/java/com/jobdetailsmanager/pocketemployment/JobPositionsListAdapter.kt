package com.jobdetailsmanager.pocketemployment

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import greendao.JobPosition

/**
 * Created by Patrick on 12/18/2016.
 */

class JobPositionsListAdapter(internal var context: Context, resource: Int, objects: List<JobPosition>) : ArrayAdapter<JobPosition>(context, resource, objects)
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var convertView = convertView
        val jobPosition = getItem(position)

        val holder: TextViewHolder

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_job_positions_info, parent, false)
            holder = TextViewHolder(convertView)
            convertView!!.tag = holder
        } else
        {
            holder = convertView.tag as TextViewHolder
        }

        holder.jobName!!.setText(jobPosition!!.jobPositionTitle)
        holder.dateApplied!!.text = ""

        if (jobPosition.employers != null)
        {
            holder.clientName!!.setText(jobPosition.employers.employerName)
        }





        return convertView
    }

    internal inner class TextViewHolder(view: View)
    {
        //@BindView(R.id.edtJobTitle)
        var jobName: EditText? = null
        //@BindView(R.id.edtClientName)
        var clientName: EditText? = null
        //@BindView(R.id.tvDateApplied)
        var dateApplied: TextView? = null

        init
        {
            //ButterKnife.bind(this, view)
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        //@OnClick(R.id.edtClientName)
        fun onClick(view: View)
        {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.menu_job_positions, popupMenu.menu)
            popupMenu.show()
        }

    }
}

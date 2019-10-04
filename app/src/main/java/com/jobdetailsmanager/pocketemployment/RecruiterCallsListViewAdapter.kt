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
import android.os.Build
import androidx.annotation.RequiresApi
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import org.joda.time.DateTime
import org.joda.time.LocalTime



/**
 * Created by Admin on 6/6/2015.
 */
//class RecruiterCallsListViewAdapter(context: Context, resource: Int, private val values: List<Contact>) : ArrayAdapter<Contact>(context, resource, values)
//{
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
//    {
//
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val rowView = inflater.inflate(R.layout.lv_row_layout, parent, false)
//
//        val tvPhoneNumber = rowView.findViewById(R.id.tvPhoneNumber) as TextView
//        val tvTime = rowView.findViewById(R.id.tvTime) as TextView
//
//        val c = values[position]
//        val number = c.formattedRecruiterPhoneNumber
//
//        val dateToTime = DateTime.parse(c.dateCallReceived).toLocalTime()
//
//        val formatTime = dateToTime.toString("h:mm a")
//
//        val formatPhoneNumber = PhoneNumberUtils.formatNumber(number, "US")
//
//        tvPhoneNumber.text = formatPhoneNumber
//        tvTime.text = formatTime
//
//        return rowView
//    }
//
//}

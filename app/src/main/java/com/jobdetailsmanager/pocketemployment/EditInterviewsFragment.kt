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


import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import kotlinx.android.synthetic.main.fragment_edit_interviews.*

class EditInterviewsFragment : Fragment()
{
    private var btnStartTime: Button? = null
    private var btnEndTime: Button? = null
    private var btnDate: Button? = null
    private var fmtTime: DateTimeFormatter? = null
    private var startDateTime: DateTime? = null
    private var endDateTime: DateTime? = null
    private val curDate: DateTime? = null

    private val startTimeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val now = DateTime.now()
        startDateTime = DateTime(now.year, now.monthOfYear, now.dayOfMonth, hourOfDay, minute, now.secondOfMinute)
        fmtTime = DateTimeFormat.shortTime()
        btnStartTime!!.text = fmtTime!!.print(startDateTime)
    }

    private val endTimeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val now = DateTime.now()
        endDateTime = DateTime(now.year, now.monthOfYear, now.dayOfMonth, hourOfDay, minute, now.secondOfMinute)
        if (endDateTime!!.isBefore(startDateTime))
        {
            endDateTime = DateTime(startDateTime!!.year, startDateTime!!.monthOfYear, startDateTime!!.dayOfMonth, startDateTime!!.plusHours(1).hourOfDay, startDateTime!!.minuteOfHour, startDateTime!!.secondOfMinute)
        }
        fmtTime = DateTimeFormat.shortTime()
        btnEndTime!!.text = fmtTime!!.print(endDateTime)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        startDateTime = setDateTime(year, monthOfYear + 1, dayOfMonth, startDateTime)
        endDateTime = setDateTime(year, monthOfYear + 1, dayOfMonth, endDateTime)

        fmtTime = DateTimeFormat.mediumDate()
        btnDate!!.text = fmtTime!!.print(startDateTime)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater!!.inflate(R.layout.fragment_edit_interviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        //acp = new AndroidCalendarProvider(getActivity());

//        val btnAddToCalendar = activity.findViewById<View>(R.id.btnAddToCalendar) as Button
//
//        val edtInterviewTitle = activity.findViewById<EditText>(R.id.edtInterviewTitle)
//        btnStartTime = activity.findViewById(R.id.btnStartTime)
//        btnEndTime = activity.findViewById(R.id.btnEndTime)
//        btnDate = activity.findViewById(R.id.dtpInterviewDate)

        //If interview exists, set existing data, if no arguments are passed, we are creating a new interview
        val bundle = arguments
        if (bundle != null)
        {
//            val interview = bundle.getSerializable("Interview") as Interview
//            if (interview != null)
//            {
//                edtInterviewTitle.setText(interview.interviewType)
//                btnStartTime!!.text = interview.startTime
//                btnEndTime!!.text = interview.endTime
//            }
        }

        val dateDialog = DatePickerDialog(activity!!, dateSetListener, DateTime.now().year, DateTime.now().monthOfYear, DateTime.now().dayOfMonth)
        val startTimeDialog = TimePickerDialog(activity, TimePickerDialog.THEME_HOLO_LIGHT, startTimeListener, DateTime.now().hourOfDay, DateTime.now().minuteOfHour, false)
        val endTimeDialog = TimePickerDialog(activity, TimePickerDialog.THEME_HOLO_LIGHT, endTimeListener, DateTime.now().hourOfDay, DateTime.now().minuteOfHour, false)

        btnStartTime!!.setOnClickListener { startTimeDialog.show() }

        btnEndTime!!.setOnClickListener { endTimeDialog.show() }

        btnAddToCalendar.setOnClickListener {
            val acp = AndroidCalendarProvider(activity!!)

            if (startDateTime != null && endDateTime != null)
            {
                //acp.insertCalendarEvent(edtInterviewTitle.getText().toString(), startDateTime, endDateTime);
                acp.setCalendarEvent(startDateTime!!, endDateTime!!, edtInterviewTitle.text.toString(), "", "")
            }
        }

        btnDate!!.setOnClickListener { dateDialog.show() }

    }


    fun setDateTime(year: Int, monthOfYear: Int, dayOfMonth: Int, dateTime: DateTime?): DateTime
    {
        val hourOfDay: Int
        val minuteOfHour: Int
        val secondOfMinute: Int

        if (dateTime != null)
        {
            hourOfDay = startDateTime!!.hourOfDay
            secondOfMinute = startDateTime!!.secondOfMinute
            minuteOfHour = startDateTime!!.minuteOfHour
        } else
        {
            val now = DateTime.now()

            hourOfDay = now.hourOfDay
            secondOfMinute = now.secondOfMinute
            minuteOfHour = now.minuteOfHour
        }

        return DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute)
    }
}

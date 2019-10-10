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
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi

import kotlinx.android.synthetic.main.fragment_edit_interviews.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
class EditInterviewsFragment : Fragment()
{
    private var btnStartTime: Button? = null
    private var btnEndTime: Button? = null
    private var btnDate: Button? = null
    private var fmtTime: DateTimeFormatter? = null
    private var startDateTime: LocalDateTime? = null
    private var endDateTime: LocalDateTime? = null
    private val curDate: LocalDateTime? = null


    private val startTimeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val now = LocalDateTime.now()

        startDateTime = LocalDateTime.of(now.year, now.month, now.dayOfMonth, hourOfDay, minute, now.second)

        fmtTime = DateTimeFormatter.ISO_DATE_TIME
        btnStartTime!!.text = startDateTime?.format(fmtTime)
    }

    private val endTimeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val now = LocalDateTime.now()
        endDateTime = LocalDateTime.of(now.year, now.month, now.dayOfMonth, hourOfDay, minute, now.second)
        if (endDateTime!!.isBefore(startDateTime))
        {
            endDateTime = LocalDateTime.of(startDateTime!!.year, startDateTime!!.month, startDateTime!!.dayOfMonth, startDateTime!!.plusHours(1).hour, startDateTime!!.minute, startDateTime!!.second)
        }
        fmtTime = DateTimeFormatter.ISO_LOCAL_TIME
        btnEndTime!!.text = endDateTime?.format(fmtTime)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        startDateTime = setDateTime(year, monthOfYear + 1, dayOfMonth, startDateTime)
        endDateTime = setDateTime(year, monthOfYear + 1, dayOfMonth, endDateTime)

        fmtTime = DateTimeFormatter.ISO_DATE
        btnDate!!.text = startDateTime?.format(fmtTime)
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

        val dateDialog = DatePickerDialog(activity!!, dateSetListener, LocalDateTime.now().year, LocalDateTime.now().monthValue, LocalDateTime.now().dayOfMonth)
        val startTimeDialog = TimePickerDialog(activity, TimePickerDialog.THEME_HOLO_LIGHT, startTimeListener, LocalDateTime.now().hour, LocalDateTime.now().minute, false)
        val endTimeDialog = TimePickerDialog(activity, TimePickerDialog.THEME_HOLO_LIGHT, endTimeListener, LocalDateTime.now().hour, LocalDateTime.now().minute, false)

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


    fun setDateTime(year: Int, monthOfYear: Int, dayOfMonth: Int, dateTime: LocalDateTime?): LocalDateTime
    {
        val hourOfDay: Int
        val minuteOfHour: Int
        val secondOfMinute: Int

        if (dateTime != null)
        {
            hourOfDay = startDateTime!!.hour
            secondOfMinute = startDateTime!!.second
            minuteOfHour = startDateTime!!.minute
        } else
        {
            val now = LocalDateTime.now()

            hourOfDay = now.hour
            secondOfMinute = now.second
            minuteOfHour = now.minute
        }

        return LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute)
    }
}

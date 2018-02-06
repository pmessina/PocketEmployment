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

import android.Manifest
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.provider.CalendarContract
import android.support.annotation.StringRes
import android.support.v4.content.CursorLoader
import android.support.v4.util.ArrayMap
import android.util.Log
import android.widget.Toast
import greendao.Interview
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions
import java.util.*


//TODO: Make sure runtime permissions are handled by the caller
class AndroidCalendarProvider(internal var context: Context)
{

    //DatabaseHelper helper;

    internal var helper: GreenDaoHelper? = null

    internal var cursor: Cursor? = null

    val eventsCurrentWeekCursorLoader: CursorLoader
        get()
        {
            val currentDay = DateTime.now()

            val dayOfWeek = currentDay.dayOfWeek().get()
            val startOfWeek = currentDay.minusDays(dayOfWeek - 1)
            val endOfWeek = currentDay.plusDays(7 - dayOfWeek)

            return getCalendarEventsCursorLoader(startOfWeek, endOfWeek)
        }

    //Get Events during the current day and rest of the month
    val eventsCurrentMonthCursorLoader: CursorLoader
        get()
        {
            val currentDay = DateTime.now().dayOfMonth().withMinimumValue()
            val endOfMonth = DateTime.now().dayOfMonth().withMaximumValue()

            return getCalendarEventsCursorLoader(currentDay, endOfMonth)
        }

    val eventsTodayCursorLoader: CursorLoader
        get()
        {
            val startOfDay = DateTime.now().withTimeAtStartOfDay()

            return getCalendarEventsCursorLoader(startOfDay, startOfDay.plusDays(1))
        }

    //Accounts permission handled in Calling Fragment
    private val userEmailAddress: String
        get()
        {
            val accountManager = AccountManager.get(context)

            val accounts = accountManager.getAccountsByType("com.google")

            return accounts[0].name
        }

    fun fillInterviewDB(cursor: Cursor)
    {
        cursor.moveToFirst()

        while (cursor.moveToNext())
        {
            val interview = Interview()
            //            try
            //            {
            interview.interviewType = cursor.getString(cursor.getColumnIndex(eventTitle))
            interview.description = cursor.getString(cursor.getColumnIndex(eventDescription))
            interview.startTime = cursor.getString(cursor.getColumnIndex(eventStartCol))
            interview.endTime = cursor.getString(cursor.getColumnIndex(eventEndCol))

            helper!!.initSession().interviewDao.insert(interview)

            //            }
            //            catch (SQLException ex)
            //            {
            //                ex.printStackTrace();
            //                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
            //            }
        }

    }

    fun fillInterviewFromCursor(cursor: Cursor, position: Int): Interview
    {
        cursor.moveToPosition(position)

        val interview = Interview()

        interview.interviewType = cursor.getString(cursor.getColumnIndex(eventTitle))
        interview.description = cursor.getString(cursor.getColumnIndex(eventDescription))
        interview.startTime = cursor.getString(cursor.getColumnIndex(eventStartCol))
        interview.endTime = cursor.getString(cursor.getColumnIndex(eventEndCol))

        return interview
    }


    //Get all interview calendar events in a week that have the title with "Interview" in them
    fun getCalendarEventsCursor(startTime: DateTime, endTime: DateTime): Cursor?
    {
        val cr = context.contentResolver

        val projection = arrayOf(Id, calId, eventTitle, eventStartCol, eventEndCol, eventDescription)

        val query = StringBuilder()
        query.append(eventStartCol).append(" BETWEEN ")
        query.append(startTime.millis).append(" AND ")
        query.append(endTime.millis).append(" AND ")
        query.append(eventAllDay + " = 0").append(" AND ")
        query.append(eventTitle).append(" LIKE ?")

        val arguments = arrayOf("%interview%")

        cursor = cr.query(calendarUri, projection, query.toString(), arguments, eventStartCol + " ASC")

        return cursor
    }


    @SuppressLint("MissingPermission")
    //Make sure permission is handled by the caller
    fun insertCalendarEvent(interviewTitle: String, startTime: DateTime, endTime: DateTime)
    {
        val cr = context.contentResolver
        val cv = ContentValues()
        cv.put(calDisplayName, userEmailAddress)
        cv.put(eventTitle, interviewTitle)
        cv.put(eventStartCol, startTime.millis)
        cv.put(eventEndCol, endTime.millis)
        cv.put(eventTimeZone, DateTimeZone.getDefault().toString())

//                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED)
//                {
        cr.insert(CalendarContract.Events.CONTENT_URI, cv)
//                } else
//                {
//                    EasyPermissions.requestPermissions(this, "Need permissions", RC_READ_CALENDER, new String[]{Manifest.permission.READ_CALENDAR});
//                }

    }

    //TODO: Move to the caller
//    @OnShowRationale(Manifest.permission.READ_CALENDAR)
//    fun showRationaleForReadCalendar(request: PermissionRequest)
//    {
//        AlertDialog.Builder(context)
//                .setPositiveButton("Yes") { _, _ -> request.proceed() }
//                .setNegativeButton("No") { _, _ -> request.cancel() }
//                .setCancelable(false)
//                .setMessage("Permission for reading calendar is needed")
//                .show()
//
//    }

    fun getCalendarEventsCursorLoader(startTime: DateTime, endTime: DateTime): CursorLoader
    {
        val projection = arrayOf(Id, calId, eventTitle, eventStartCol, eventEndCol, eventDescription)

        val query = StringBuilder()
        query.append(eventStartCol).append(" >= ")
        query.append(startTime.millis).append(" AND ")
        query.append(eventStartCol).append(" <= ")
        query.append(endTime.millis)
        //.append(" AND ");
        //        query.append(eventAllDay + " = 0").append(" AND ");
        //        query.append(eventTitle).append(" LIKE ?");

        val arguments: Array<String>? = null// new String[]{"LOWER(%interview%)"};

        return CursorLoader(context, calendarUri, projection, null, arguments, eventStartCol + " ASC")
    }

    //Get all interview calendar events in a week that have the title with "Interview" in them
    fun getCalendarEvents(startTime: DateTime, endTime: DateTime): List<Interview>
    {
        val cr = context.contentResolver

        val projection = arrayOf(calId, eventTitle, eventStartCol, eventEndCol, eventDescription)

        val query = StringBuilder()
        query.append(eventStartCol).append(" BETWEEN ")
        query.append(startTime.millis).append(" AND ")
        query.append(endTime.millis).append(" AND ")
        query.append(eventAllDay + " = 0").append(" AND ")
        query.append(eventTitle).append(" LIKE ?")

        val results = ArrayList<Interview>()

        val arguments = arrayOf("%interview%")

        cursor = cr.query(calendarUri, projection, query.toString(), arguments, eventStartCol + " ASC")

        try
        {
            Log.i("Cursor Query", "Cursor returned " + cursor!!.count + " rows")

            while (cursor!!.moveToNext())
            {
                //Interview acts as a holder, will not be persisted to database
                val interview = Interview()
                val eventStart = cursor!!.getString(cursor!!.getColumnIndex(eventStartCol))

                val startTimeDT = DateTime(java.lang.Long.parseLong(eventStart))
                interview.startTime = startTimeDT.toString()

                val eventEnd = cursor!!.getString(cursor!!.getColumnIndex(eventEndCol))
                val endTimeDT = DateTime(java.lang.Long.parseLong(eventEnd))
                interview.endTime = endTimeDT.toString()

                val eventTitleName = cursor!!.getString(cursor!!.getColumnIndex(eventTitle))
                interview.interviewType = eventTitleName

                val eventDescriptionCol = cursor!!.getString(cursor!!.getColumnIndex(eventDescription))
                interview.description = eventDescriptionCol

                results.add(interview)
            }

            //cursor.closeSession();

        }
        catch (ex: Exception)
        {
            if (!cursor!!.isClosed)
            {
                cursor!!.close()
            }
            ex.printStackTrace()
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
        }

        return results
    }


    fun setCalendarEvent(beginTime: DateTime, endTime: DateTime, title: String, description: String, eventLocation: String)
    {
        val parseBeginTime = beginTime.millis
        val parseEndTime = endTime.millis

        val intent = Intent(Intent.ACTION_INSERT).addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setData(CalendarContract.Events.CONTENT_URI).putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, parseBeginTime).putExtra(CalendarContract.EXTRA_EVENT_END_TIME, parseEndTime).putExtra(CalendarContract.Events.TITLE, title).putExtra(CalendarContract.Events.DESCRIPTION, description).putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation).putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY).putExtra(CalendarContract.Reminders.HAS_ALARM, 0)
        //.putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        context.startActivity(intent)

    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.READ_CALENDAR)
    fun AddEvent()
    {
        val cr = context.contentResolver
        val cv = ContentValues()
        cv.put(calDisplayName, userEmailAddress)
        cv.put(eventStartCol, DateTime.now().toDate().toString())
        cv.put(eventEndCol, DateTime.now().plusHours(1).toDate().toString())
        cv.put(eventTimeZone, DateTimeZone.getDefault().toString())

        cr.insert(CalendarContract.Events.CONTENT_URI, cv)
    }

    fun getEventsGroupByDate(interviews: MutableList<Interview>): ArrayMap<LocalDate, MutableList<Interview>>
    {
        val recruiterInterviewDates = ArrayMap<LocalDate, MutableList<Interview>>()

        for (c in interviews)
        {
            val localDate = c.interviewDate
            if (recruiterInterviewDates[localDate] == null)
            {
                recruiterInterviewDates[localDate] = mutableListOf()
            }
            recruiterInterviewDates[localDate]!!.add(c)
        }

        return recruiterInterviewDates
    }

    companion object
    {
        private val calendarUri = CalendarContract.Events.CONTENT_URI
        private val calDisplayName = CalendarContract.Events.CALENDAR_DISPLAY_NAME
        private val calId = CalendarContract.Events.CALENDAR_ID
        private val eventStartCol = CalendarContract.Events.DTSTART
        private val eventEndCol = CalendarContract.Events.DTEND
        private val eventTitle = CalendarContract.Events.TITLE
        private val eventDescription = CalendarContract.Events.DESCRIPTION
        private val eventTimeZone = CalendarContract.Events.EVENT_TIMEZONE
        private val eventAllDay = CalendarContract.Events.ALL_DAY
        private val Id = CalendarContract.Events._ID
        private val reminder = CalendarContract.Events.ALLOWED_REMINDERS

        val RC_READ_CALENDER = 120
    }

}

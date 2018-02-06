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

import android.annotation.TargetApi
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.twotoasters.sectioncursoradapter.adapter.SectionCursorAdapter
import com.twotoasters.sectioncursoradapter.adapter.viewholder.ViewHolder

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

@TargetApi(value = 21)
class InterviewsCursorAdapter(internal var context: Context, cursor: Cursor) : SectionCursorAdapter<String, InterviewsCursorAdapter.InterviewDateViewHolder, InterviewsCursorAdapter.InterviewViewHolder>(context, cursor, 0, R.layout.interviews_date_tv_separator, R.layout.interviews_lv_row_layout)
{

    override fun getSectionFromCursor(cursor: Cursor): String
    {
        val dateFormatter = DateTimeFormat.longDate()
        val eventStart = cursor.getString(cursor.getColumnIndex(eventStartCol))

        return dateFormatter.print(java.lang.Long.parseLong(eventStart))
    }

    override fun createSectionViewHolder(sectionView: View, section: String): InterviewDateViewHolder
    {
        return InterviewDateViewHolder(sectionView)
    }

    override fun bindSectionViewHolder(position: Int, sectionViewHolder: InterviewDateViewHolder, parent: ViewGroup, section: String)
    {
        sectionViewHolder.interviewDate.setOnClickListener(null)
        sectionViewHolder.interviewDate.text = section
    }

    override fun createItemViewHolder(cursor: Cursor, itemView: View): InterviewViewHolder
    {

        return InterviewViewHolder(itemView)
    }


    override fun bindItemViewHolder(itemViewHolder: InterviewViewHolder, cursor: Cursor, parent: ViewGroup)
    {
        if (itemViewHolder.interviewType != null && itemViewHolder.timeStart != null && itemViewHolder.timeEnd != null)
        {
            val eventStart = cursor.getString(cursor.getColumnIndex(eventStartCol))
            val eventEnd = cursor.getString(cursor.getColumnIndex(eventEndCol))
            val eventTitleName = cursor.getString(cursor.getColumnIndex(eventTitle))

            val timeFormatter = DateTimeFormat.shortTime()
            val startTimeDT = DateTime(java.lang.Long.parseLong(eventStart))
            val endTimeDT = DateTime(java.lang.Long.parseLong(eventEnd))
            val eventStartTF = timeFormatter.print(startTimeDT)
            val eventEndTF = timeFormatter.print(endTimeDT)

            itemViewHolder.timeStart.text = eventStartTF
            itemViewHolder.timeStart.setOnClickListener(null)

            itemViewHolder.timeEnd.text = eventEndTF
            itemViewHolder.timeEnd.setOnClickListener(null)

            itemViewHolder.interviewType.text = eventTitleName
            itemViewHolder.interviewType.setOnClickListener(null)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        Toast.makeText(context, this.count.toString() + "", Toast.LENGTH_SHORT).show()

        if (convertView != null)
        {
            this.count
            Toast.makeText(context, position.toString() + ", " + convertView.toString() + "", Toast.LENGTH_SHORT).show()
        } else
        {
            Toast.makeText(context, position.toString() + " convertView is null", Toast.LENGTH_SHORT).show()
        }

        return super.getView(position, convertView, parent)
    }

    //    @Deprecated
    //    protected View newSectionView(Context context, Object o, ViewGroup parent)
    //    {
    //        return LayoutInflater.from(context).inflate(R.layout.interviews_date_tv_separator, parent, false);
    //    }
    //
    //    @Deprecated
    //    protected void bindSectionView(View view, Context context, int i, Object o)
    //    {
    //        TextView interviewDate = (TextView) view.findViewById(R.id.tvInterviewDate);
    //        interviewDate.setOnClickListener(null);
    //        interviewDate.setText(o.toString());
    //    }
    //
    //    @Deprecated
    //    protected View newItemView(Context context, Cursor cursor, ViewGroup parent)
    //    {
    //        return LayoutInflater.from(context).inflate(R.layout.interviews_lv_row_layout, parent, false);
    //    }
    //
    //    @Deprecated
    //    protected void bindItemView(View view, Context context, Cursor cursor)
    //    {
    //        TextView interviewType = (TextView)view.findViewById(R.id.tvInterviewType);
    //        TextView timeStart = (TextView)view.findViewById(R.id.tvStartTime);
    //        TextView timeEnd = (TextView)view.findViewById(R.id.tvEndTime);
    //
    //        if (interviewType != null && timeStart != null && timeEnd != null)
    //        {
    //            String eventStart = cursor.getString(cursor.getColumnIndex(eventStartCol));
    //            String eventEnd = cursor.getString(cursor.getColumnIndex(eventEndCol));
    //            String eventTitleName = cursor.getString(cursor.getColumnIndex(eventTitle));
    //
    //            DateTimeFormatter timeFormatter = DateTimeFormat.shortTime();
    //            DateTime startTimeDT = new DateTime(Long.parseLong(eventStart));
    //            DateTime endTimeDT = new DateTime(Long.parseLong(eventEnd));
    //            String eventStartTF = timeFormatter.print(startTimeDT);
    //            String eventEndTF = timeFormatter.print(endTimeDT);
    //
    //            timeStart.setText(eventStartTF);
    //            timeEnd.setText(eventEndTF);
    //            interviewType.setText(eventTitleName);
    //        }
    //    }

    inner class InterviewViewHolder(rootView: View) : ViewHolder(rootView)
    {
        val interviewType: TextView?
        val timeStart: TextView?
        val timeEnd: TextView?

        init
        {
            interviewType = findWidgetById(R.id.tvInterviewType)
            timeStart = findWidgetById(R.id.tvStartTime)
            timeEnd = findWidgetById(R.id.tvEndTime)
        }
    }

    inner class InterviewDateViewHolder(rootView: View) : ViewHolder(rootView)
    {
        var interviewDate: TextView

        init
        {
            interviewDate = findWidgetById(R.id.tvInterviewDate)
        }
    }

    companion object
    {
        private val calendarUri = CalendarContract.Events.CONTENT_URI
        private val calDisplayName = CalendarContract.Events.CALENDAR_DISPLAY_NAME
        private val eventID = CalendarContract.Events._ID
        private val eventStartCol = CalendarContract.Events.DTSTART
        private val eventEndCol = CalendarContract.Events.DTEND
        private val eventTitle = CalendarContract.Events.TITLE
        private val eventDescription = CalendarContract.Events.DESCRIPTION
        private val eventTimeZone = CalendarContract.Events.EVENT_TIMEZONE
        private val eventAllDay = CalendarContract.Events.ALL_DAY
    }
}


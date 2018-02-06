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
import android.content.Intent
import android.database.Cursor
import android.graphics.Typeface
import android.telephony.PhoneNumberUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jobdetailsmanager.pocketemployment.CallsLogContentProvider.*
import com.twotoasters.sectioncursoradapter.adapter.SectionCursorAdapter
import com.twotoasters.sectioncursoradapter.adapter.viewholder.ViewHolder
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

@TargetApi(value = 21)
class CallsCursorAdapter(internal var context: Context, cursor: Cursor?) : SectionCursorAdapter<CallRow, CallsCursorAdapter.CallsDateViewHolder, CallsCursorAdapter.CallsDetailsViewHolder>(context, cursor, 0, R.layout.recruiter_contact_info_group, R.layout.recruiter_contact_info)
{
    override fun getSectionFromCursor(callsLogCursor: Cursor): CallRow
    {
        val date = callsLogCursor.getString(callsLogCursor.getColumnIndex(CALLS_LOG_DATE))

        val callRow = CallRow()

        callRow.callDate = DateTime(java.lang.Long.parseLong(date))

        return callRow
    }

    override fun createSectionViewHolder(sectionView: View, section: CallRow): CallsDateViewHolder
    {
        return CallsDateViewHolder(sectionView)
    }

    override fun bindSectionViewHolder(position: Int, sectionViewHolder: CallsDateViewHolder, parent: ViewGroup, section: CallRow)
    {
        sectionViewHolder.lblListHeader.setTypeface(null, Typeface.BOLD_ITALIC)

        val formatter = DateTimeFormat.fullDate()
        val formattedHeaderTitle = formatter.print(DateTime(section.callDate))

        sectionViewHolder.lblListHeader.setText(formattedHeaderTitle)

    }

    override fun createItemViewHolder(cursor: Cursor, itemView: View): CallsDetailsViewHolder
    {

        itemView.setOnClickListener {
            val tvPhoneNumber = itemView.findViewById<View>(R.id.tvPhoneNumber) as TextView
            //                cursor.moveToPosition(itemView.getId());
            //                String number = cursor.getString(cursor.getColumnIndex(CALLS_LOG_NUMBER));
            val intent = Intent(context, RecruiterCallsDetailsActivity::class.java)
            intent.putExtra("phoneNumber", tvPhoneNumber.text.toString())
            context.startActivity(intent)
        }

        return CallsDetailsViewHolder(itemView)
    }


    override fun bindItemViewHolder(itemViewHolder: CallsDetailsViewHolder, callsLogCursor: Cursor, parent: ViewGroup)
    {
        val number = callsLogCursor.getColumnIndex(CALLS_LOG_NUMBER)
        val name = callsLogCursor.getColumnIndex(CALLS_LOG_CACHED_NAME)

        val type = callsLogCursor.getColumnIndex(CALLS_LOG_TYPE)
        val date = callsLogCursor.getColumnIndex(CALLS_LOG_DATE)
        val duration = callsLogCursor.getColumnIndex(CALLS_LOG_DURATION)
        val geoLocation = callsLogCursor.getColumnIndex(CALLS_LOG_GEOCODED_LOCATION)

        val formattedNumber = PhoneNumberUtils.formatNumber(callsLogCursor.getString(number), Locale.getDefault().country)

        itemViewHolder.callPhoneNumber.text = formattedNumber


        //itemViewHolder.setCallType(callsLogCursor.getString(type));

        if (name > -1)
        {
            itemViewHolder.callName.text = callsLogCursor.getString(name)
        }

        val time = DateTime(java.lang.Long.parseLong(callsLogCursor.getString(date)))

        val formatter = DateTimeFormat.shortTime()
        val formattedTime = formatter.print(time)

        itemViewHolder.callDate.text = formattedTime
        //itemViewHolder.setCallDuration(callsLogCursor.getString(duration));
        //itemViewHolder.setCallGeoLocation(callsLogCursor.getString(geoLocation));
    }


    inner class CallsDetailsViewHolder(rootView: View) : ViewHolder(rootView)
    {
        val callName: TextView
        val callPhoneNumber: TextView
        val callDate: TextView

        init
        {
            callName = findWidgetById(R.id.tvName)
            callPhoneNumber = findWidgetById(R.id.tvPhoneNumber)
            callDate = findWidgetById(R.id.tvTime)
        }
    }

    inner class CallsDateViewHolder(rootView: View) : ViewHolder(rootView)
    {
        val lblListHeader: TextView

        init
        {
            lblListHeader = findWidgetById(R.id.tvDate)
        }
    }

}


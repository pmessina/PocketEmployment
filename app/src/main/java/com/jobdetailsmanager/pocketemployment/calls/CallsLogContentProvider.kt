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

package com.jobdetailsmanager.pocketemployment.calls

import android.content.Context
import android.os.Build
import android.provider.CallLog
import androidx.annotation.RequiresApi
import androidx.loader.content.CursorLoader
import com.jobdetailsmanager.pocketemployment.millis
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
class CallsLogContentProvider(internal var context: Context) {

    //DateTime now = DateTime.now().withTimeAtStartOfDay();
    //String.valueOf(now.getMillis());
    //String.valueOf(now.plusDays(1).getMillis());


    private val currentDay: LocalDateTime = LocalDateTime.now()

    val callsToday: CursorLoader
        get() {

            val startOfDay = currentDay.toLocalDate().atStartOfDay()
            val endOfDay = startOfDay.plusDays(1)

            val cursor = CursorLoader(context, CallLog.Calls.CONTENT_URI, arrayOf(CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION),
                    CALLS_LOG_DATE + " >= " + "'" + startOfDay.millis + "'" + " AND " + CALLS_LOG_DATE + " <= " + "'" + endOfDay.millis + "'" + " AND " +
                            CALLS_LOG_TYPE + " != " + callsLogVoiceMailType, null, "$CALLS_LOG_DATE ASC")


            //context.contentResolver.notifyChange(CallLog.Calls.CONTENT_URI, null)

            return cursor
        }


    val allCallsToday: CursorLoader
        get() = CursorLoader(context, CallLog.Calls.CONTENT_URI, arrayOf(CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION), null, null, "$CALLS_LOG_DATE ASC")


    //    public ArrayMap<String, List<Contact>> groupByDateCallsLog(List<CallRow> callRows)
    //    {
    //        List<Contact> fetchDates = new ArrayList<>();
    //
    //        ArrayMap<String, List<Contact>> recruiterContactDates = new ArrayMap<>();
    //
    //        //TODO: Fix callRows null
    //        CallRow tempCallRow = callRows.get(0);
    //
    //        for (CallRow currentCallRow : callRows)
    //        {
    //            try
    //            {
    //                DateTime tempCallDate = tempCallRow.getCallDate();
    //                DateTime currentDateTime = currentCallRow.getCallDate();
    //
    //                DateTimeFormatter format = DateTimeFormat.fullDate();
    //
    //                if (format.print(tempCallDate).equals(format.print(currentDateTime)))
    //                {
    //                    Contact contact = new Contact();
    //                    contact.setDateCallReceived(currentDateTime.toString());
    //                    contact.setContactPhoneNumber(currentCallRow.getPhoneNumber());
    //                    contact.setContactType(currentCallRow.getCallType());
    //                    fetchDates.add(contact);
    //                    //fetchDates.add(currentCallRow.getCallDate().toLocalTime().toString("hh:mm:ss"));
    //                }
    //                else
    //                {
    //                    if (!fetchDates.isEmpty())
    //                    {
    //                        recruiterContactDates.put(currentDateTime.toString(), fetchDates);
    //                        fetchDates = new ArrayList<>();
    //                    }
    //                    //since the calls row dates don't match, update temporary variable for comparison to next call row
    //                    tempCallRow = currentCallRow;
    //                }
    //
    //                if (recruiterContactDates.size() == 0 && !fetchDates.isEmpty())
    //                {
    //                    recruiterContactDates.put(currentDateTime.toString(), fetchDates);
    //                }
    //
    //            }
    //            catch(Exception ex)
    //            {
    //                ex.printStackTrace();
    //            }
    //        }
    //
    //        return recruiterContactDates;
    //    }

    val calls: CursorLoader
    get(){
        return CursorLoader(context, CallLog.Calls.CONTENT_URI, null, null, null, null)
    }
    val callsThisWeek: CursorLoader
        get() {

            val currentDay = LocalDateTime.now()

            val dayOfWeek = currentDay.dayOfWeek

            val startOfWeek = currentDay.minusDays(1)
            val endOfWeek = currentDay.plusDays(7L - dayOfWeek.value)

            return CursorLoader(context, CallLog.Calls.CONTENT_URI, arrayOf(CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION),
                    CALLS_LOG_DATE + " >= " + "'" + startOfWeek.millis + "'" + " AND " + CALLS_LOG_DATE + " <= " + "'" + endOfWeek.millis + "'" + " AND " +
                            CALLS_LOG_TYPE + " != " + callsLogVoiceMailType, null, "$CALLS_LOG_DATE ASC")

        }

    val callsThisMonth: CursorLoader
        get() {

            val currentDay = LocalDateTime.now()

            val startOfMonth = currentDay.with(TemporalAdjusters.firstDayOfMonth())
            val endOfMonth = currentDay.with(TemporalAdjusters.lastDayOfMonth())

            return CursorLoader(context, CallLog.Calls.CONTENT_URI, arrayOf(CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION),
                    CALLS_LOG_DATE + " >= " + "'" + startOfMonth.millis + "'" + " AND " + CALLS_LOG_DATE + " <= " + "'" + endOfMonth.millis + "'" + " AND " +
                            CALLS_LOG_TYPE + " != " + callsLogVoiceMailType, null, "$CALLS_LOG_DATE ASC")

        }

    companion object {
        const val CALLS_LOG_ID = CallLog.Calls._ID //Needed internally by SectionCursorAdapter
        const val CALLS_LOG_NUMBER = CallLog.Calls.NUMBER
        const val CALLS_LOG_TYPE = CallLog.Calls.TYPE
        const val CALLS_LOG_DATE = CallLog.Calls.DATE
        const val CALLS_LOG_CACHED_NAME = CallLog.Calls.CACHED_NAME
        const val CALLS_LOG_DURATION = CallLog.Calls.DURATION
        const val CALLS_LOG_GEOCODED_LOCATION = CallLog.Calls.GEOCODED_LOCATION
        const val callsLogVoiceMailType = CallLog.Calls.VOICEMAIL_TYPE
    }

}

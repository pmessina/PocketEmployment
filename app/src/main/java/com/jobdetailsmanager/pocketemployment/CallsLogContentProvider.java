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

package com.jobdetailsmanager.pocketemployment;

import android.content.Context;
import android.provider.CallLog;
import android.support.v4.content.CursorLoader;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import greendao.Contact;


public class CallsLogContentProvider
{
    public static final String CALLS_LOG_ID = CallLog.Calls._ID; //Needed internally by SectionCursorAdapter
    public static final String CALLS_LOG_NUMBER = CallLog.Calls.NUMBER;
    public static final String CALLS_LOG_TYPE = CallLog.Calls.TYPE;
    public static final String CALLS_LOG_DATE = CallLog.Calls.DATE;
    public static final String CALLS_LOG_CACHED_NAME = CallLog.Calls.CACHED_NAME;
    public static final String CALLS_LOG_DURATION = CallLog.Calls.DURATION;
    public static final String CALLS_LOG_GEOCODED_LOCATION = CallLog.Calls.GEOCODED_LOCATION;
    public static final int callsLogVoiceMailType = CallLog.Calls.VOICEMAIL_TYPE;

    Context context;

    public CallsLogContentProvider(Context context)
    {
        this.context = context;
    }

    public CursorLoader getCallsToday()
    {
        DateTime now = DateTime.now().withTimeAtStartOfDay();
        String startOfDay = String.valueOf(now.getMillis());
        String endOfDay = String.valueOf(now.plusDays(1).getMillis());

        CursorLoader cursor = new CursorLoader(context,  CallLog.Calls.CONTENT_URI, new String[]{CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION},
                CALLS_LOG_DATE + " >= " + "'"+startOfDay +"'"+ " AND " + CALLS_LOG_DATE + " <= " + "'"+endOfDay+"'" + " AND " +
                        CALLS_LOG_TYPE + " != " +  callsLogVoiceMailType, null, CALLS_LOG_DATE + " ASC");


        context.getContentResolver().notifyChange(CallLog.Calls.CONTENT_URI, null);

        return cursor;
    }

    public CursorLoader getAllCallsToday()
    {
        DateTime now = DateTime.now().withTimeAtStartOfDay();
        String startOfDay = String.valueOf(now.getMillis());
        String endOfDay = String.valueOf(now.plusDays(1).getMillis());

        CursorLoader cursor = new CursorLoader(context,  CallLog.Calls.CONTENT_URI, new String[]{CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION},
                null, null, CALLS_LOG_DATE + " ASC");

        return cursor;
    }


    public ArrayMap<String, List<Contact>> groupByDateCallsLog(List<CallRow> callRows)
    {
        List<Contact> fetchDates = new ArrayList<>();

        ArrayMap<String, List<Contact>> recruiterContactDates = new ArrayMap<>();

        //TODO: Fix callRows null
        CallRow tempCallRow = callRows.get(0);

        for (CallRow currentCallRow : callRows)
        {
            try
            {
                DateTime tempCallDate = tempCallRow.getCallDate();
                DateTime currentDateTime = currentCallRow.getCallDate();

                DateTimeFormatter format = DateTimeFormat.fullDate();

                if (format.print(tempCallDate).equals(format.print(currentDateTime)))
                {
                    Contact contact = new Contact();
                    contact.setDateCallReceived(currentDateTime.toString());
                    contact.setContactPhoneNumber(currentCallRow.getPhoneNumber());
                    contact.setContactType(currentCallRow.getCallType());
                    fetchDates.add(contact);
                    //fetchDates.add(currentCallRow.getCallDate().toLocalTime().toString("hh:mm:ss"));
                }
                else
                {
                    if (!fetchDates.isEmpty())
                    {
                        recruiterContactDates.put(currentDateTime.toString(), fetchDates);
                        fetchDates = new ArrayList<>();
                    }
                    //since the calls row dates don't match, update temporary variable for comparison to next call row
                    tempCallRow = currentCallRow;
                }

                if (recruiterContactDates.size() == 0 && !fetchDates.isEmpty())
                {
                    recruiterContactDates.put(currentDateTime.toString(), fetchDates);
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return recruiterContactDates;
    }

    public CursorLoader getCallsThisWeek()
    {
        DateTime currentDay = DateTime.now();

        int dayOfWeek = currentDay.dayOfWeek().get();
        DateTime startOfWeek = currentDay.minusDays(dayOfWeek - 1);
        DateTimeFormatter format = DateTimeFormat.fullDate();

        Log.i("StartWeek", format.print(startOfWeek));

        DateTime endOfWeek = currentDay.plusDays(7 - dayOfWeek);
        Log.i("EndWeek", format.print(endOfWeek));

        CursorLoader cursorLoader = new CursorLoader(context,  CallLog.Calls.CONTENT_URI, new String[]{CALLS_LOG_ID, CALLS_LOG_CACHED_NAME, CALLS_LOG_NUMBER, CALLS_LOG_TYPE, CALLS_LOG_DATE, CALLS_LOG_DURATION, CALLS_LOG_GEOCODED_LOCATION},
                CALLS_LOG_DATE + " >= " + "'"+startOfWeek.getMillis() +"'"+ " AND " + CALLS_LOG_DATE + " <= " + "'"+endOfWeek+"'" + " AND " +
                        CALLS_LOG_TYPE + " != " +  callsLogVoiceMailType, null, CALLS_LOG_DATE + " ASC");

        return cursorLoader;
    }

}

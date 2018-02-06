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

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InterviewsContentBroadcastReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        Toast.makeText(context, "Provider Changed", Toast.LENGTH_LONG).show()
        //        final String SELECTION = CalendarContract.Events.CALENDAR_ID + "="
        //                + calendarId + " AND " + "("
        //                + CalendarContract.Events.DIRTY + "=" + 1 + " OR "
        //                + CalendarContract.Events.DELETED + "=" + 1 + ")" + " AND "
        //                + CalendarContract.Events.DTEND + " > "
        //                + Calendar.getInstance().getTimeInMillis();
    }
}

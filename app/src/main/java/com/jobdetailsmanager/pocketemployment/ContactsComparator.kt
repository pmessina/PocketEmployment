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

import org.joda.time.DateTime
import org.joda.time.LocalDate

import java.util.Comparator

import greendao.Contact

class ContactsComparator private constructor() : Comparator<Contact>
{

    override fun compare(lhs: Contact, rhs: Contact): Int
    {
        val localDate = DateTime(lhs.dateCallReceived).toLocalDate()
        val thisLocalDate = DateTime(rhs.dateCallReceived).toLocalDate()

        return localDate.compareTo(thisLocalDate)
    }

    private fun parseDate(date: String): LocalDate
    {
        return DateTime.parse(date).toLocalDate()
    }

    companion object
    {
        operator fun invoke(): ContactsComparator
        {
            return ContactsComparator()
        }
    }
}

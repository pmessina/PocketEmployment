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

package com.jobdetailsmanager.pocketemployment.database

import android.os.Build
import android.telephony.PhoneNumberUtils

import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jobdetailsmanager.pocketemployment.recruiters.RecruiterCallState

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Comparator

@Entity(foreignKeys = [ForeignKey(entity = Employer::class, parentColumns = ["employerId"], childColumns = ["contactId"], onDelete = CASCADE)])
@RequiresApi(Build.VERSION_CODES.O)
class Contact : Serializable, Comparator<Contact> {

    @PrimaryKey(autoGenerate = true)
    var contactId: Long = 0

    var employerId: Long = 0

    var recruiterFirstName: String? = null

    var recruiterLastName: String? = null

    var recruiterCompanyName: String? = null

    var recruiterCompanyAddress: String? = null

    var recruiterPhoneNumber: String? = null

    var dateCallReceived: String? = null

    var recruiterCallState: Int = 0

    val formattedRecruiterPhoneNumber: String
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        get() = PhoneNumberUtils.formatNumber(recruiterPhoneNumber, "US")

    val formattedDateCallReceived: String
        get() = LocalDateTime.parse(dateCallReceived).toLocalDate().toString()

    val formattedDayCallReceived: String
        get() = Integer.toString(LocalDateTime.parse(dateCallReceived).toLocalDate().dayOfMonth)

    val timeCallReceived: String?
        get() = if (dateCallReceived != null) LocalDateTime.parse(dateCallReceived).toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a")) else null


    fun setRecruiterCallState(recruiterCallState: RecruiterCallState){
        this.recruiterCallState = recruiterCallState.value
    }

    constructor() {}


    @Ignore
    constructor(contactId: Long, recruiterFirstName: String, recruiterLastName: String, recruiterCompanyName: String,
                recruiterCompanyAddress: String, recruiterPhoneNumber: String, dateCallReceived: String, recruiterCallState: Int) {
        this.contactId = contactId
        this.recruiterFirstName = recruiterFirstName
        this.recruiterLastName = recruiterLastName
        this.recruiterCompanyName = recruiterCompanyName
        this.recruiterCompanyAddress = recruiterCompanyAddress
        this.recruiterPhoneNumber = recruiterPhoneNumber
        this.dateCallReceived = dateCallReceived
        this.recruiterCallState = recruiterCallState
    }

    override fun toString(): String {
        return "$recruiterFirstName $recruiterLastName $recruiterCompanyAddress $recruiterPhoneNumber $dateCallReceived"
    }


    override fun compare(lhs: Contact, rhs: Contact): Int {
        val localTime = LocalDateTime.parse(lhs.dateCallReceived).toLocalTime()
        val thisLocalTime = LocalDateTime.parse(rhs.dateCallReceived).toLocalTime()

        return localTime.compareTo(thisLocalTime)
    }

    companion object {
        private const val serialVersionUID = 1L
    }


}

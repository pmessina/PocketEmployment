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
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import java.io.Serializable
import java.util.Comparator

import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "interviews", foreignKeys = [ForeignKey(entity = JobPosition::class, parentColumns = ["jobPositionId"], childColumns = ["interviewId"], onDelete = CASCADE)])
@RequiresApi(Build.VERSION_CODES.O)
class Interview : Serializable, Comparator<Interview> {

    @PrimaryKey(autoGenerate = true)
    var interviewId: Long = 0

    var jobPositionId: Long = 0

    var startTime: String? = null

    var endTime: String? = null

    var interviewType: String? = null

    var description: String? = null

    var feedback: String? = null

    //private var jobPositions: List<JobPosition>? = null

//    val startTimeDT: LocalDateTime
//        get()

    val startTimeString: String

        get() {
            val dateFormatter = DateTimeFormatter.ISO_TIME
            val startTimeDF = LocalDateTime.parse(startTime, dateFormatter).toString()
            return startTimeDF
        }


    val interviewDate: LocalDate
        get() {
            val parse = LocalDate.parse(startTime)
            return parse
        }

    val endTimeDT: LocalDateTime
        get() = LocalDateTime.parse(endTime)

    val endTimeString: String
        get() {

            val dateFormatter = DateTimeFormatter.ISO_TIME
            val endTimeDF = LocalDateTime.parse(endTime, dateFormatter).toString()

            return endTimeDF
        }

    constructor() {}

    @Ignore
    constructor(interviewId: Long, startTime: String, endTime: String,
                interviewType: String, description: String, feedback: String) {
        this.interviewId = interviewId
        this.startTime = startTime
        this.endTime = endTime
        this.interviewType = interviewType
        this.description = description
        this.feedback = feedback
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun compare(lhs: Interview, rhs: Interview): Int {
        val lhsStartTime = LocalDateTime.parse(lhs.startTime)
        val rhsStartTime = LocalDateTime.parse(rhs.startTime)

        val lhsLocalDate = lhsStartTime.toLocalDate()
        val rhsLocalDate = rhsStartTime.toLocalDate()

        return lhsLocalDate.compareTo(rhsLocalDate)
    }

    companion object {
        private const val serialVersionUID = 1L
    }

}

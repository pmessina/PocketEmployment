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


import androidx.room.Entity

@Entity(tableName = "recruiters")
class Recruiter {

    @Id(autoincrement = true)
    var recruiterId: Long = 0

    var phoneNumber: String? = null

    var emailAddress: String? = null

    private val recruiterCompany: RecruiterCompany? = null

    constructor() {}

    constructor(recruiterId: Long, phoneNumber: String, emailAddress: String) {
        this.recruiterId = recruiterId
        this.phoneNumber = phoneNumber
        this.emailAddress = emailAddress
    }

}
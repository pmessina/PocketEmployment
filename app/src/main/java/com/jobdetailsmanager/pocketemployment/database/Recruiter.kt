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


import androidx.room.*

@Entity(tableName = "recruiters",
        foreignKeys = [ForeignKey(entity = RecruiterCompany::class, parentColumns = ["recruiter_company_id"], childColumns = ["recruiter_id"])],
        indices = [Index("recruiter_id")])
data class Recruiter(
        @ColumnInfo(name = "recruiter_name")
        val recruiterName: String?,
        @ColumnInfo(name = "recruiter_phone_number")
        val recruiterPhoneNumber: String?,
        @ColumnInfo(name = "recruiter_email_address")
        val recruiterEmailAdress: String,
        @ColumnInfo(name = "recruiter_company_id")
        var recruiterCompanyId: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recruiter_id")
    var recruiterId: Long = 0




}

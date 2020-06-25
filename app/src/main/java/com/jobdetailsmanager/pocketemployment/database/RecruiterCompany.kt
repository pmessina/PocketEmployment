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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recruiter_companies")
data class RecruiterCompany(
        @ColumnInfo(name = "recruiter_company_name")
        var recruiterCompanyName: String?,
        @ColumnInfo(name = "recruiter_company_address")
        var recruiterCompanyAddress: String?,
        @ColumnInfo(name = "recruiter_company_website")
        var recruiterCompanyWebsite: String?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recruiter_company_id")
    var recruiterCompanyId: Long = 0

}

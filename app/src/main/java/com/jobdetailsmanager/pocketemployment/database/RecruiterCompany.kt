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
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jobdetailsmanager.pocketemployment.database.Recruiter


@Entity(tableName = "recruiter_companies")
class RecruiterCompany {

    @PrimaryKey(autoGenerate = true)
    var recruiterCompanyId: Int = 0

    var address: String? = null

    var website: String? = null

    //private var recruiters: List<Recruiter>? = null

    constructor() {}

    @Ignore
    constructor(recruiterCompanyId: Int, address: String,
                website: String) {
        this.recruiterCompanyId = recruiterCompanyId
        this.address = address
        this.website = website
    }

}

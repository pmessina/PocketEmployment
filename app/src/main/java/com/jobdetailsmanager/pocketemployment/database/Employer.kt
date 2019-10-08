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
import androidx.room.PrimaryKey

@Entity(tableName = "employers")
class Employer {

    @PrimaryKey(autoGenerate = true)
    var employerId: Long = 0

    var employerName: String? = null

    var employerAddress: String? = null

    var employerDescription: String? = null

    private val contact: Contact? = null

    constructor() {}

    constructor(employerId: Long, employerName: String, employerAddress: String,
                employerDescription: String) {
        this.employerId = employerId
        this.employerName = employerName
        this.employerAddress = employerAddress
        this.employerDescription = employerDescription
    }

    override fun toString(): String {
        return "$employerName $employerAddress"
    }


}

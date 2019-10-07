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
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import androidx.room.ForeignKey.CASCADE
import com.jobdetailsmanager.pocketemployment.database.Employer
import com.jobdetailsmanager.pocketemployment.database.Interview

@Entity(tableName = "job_positions", foreignKeys = [ForeignKey(entity = Employer::class, parentColumns = ["id"], childColumns = ["id"], onDelete = CASCADE),
                                                    ForeignKey(entity = Interview::class, parentColumns = ["id"], childColumns = ["id"], onDelete = CASCADE)])
class JobPosition {

    //    public Employer getEmployer()
    //    {
    //        return employer;
    //    }

    //    public void setEmployer(Employer employer)
    //    {
    //        this.employer = employer;
    //    }

    @PrimaryKey(autoGenerate = true)
    var jobPositionId: Long = 0

    /** Used to resolve relations  */
    //    @Generated(hash = 2040040024)
    //    private transient DaoSession daoSession;
    //
    //    /** Used for active entity operations. */
    //    @Generated(hash = 2120035555)
    //    private transient JobPositionDao myDao;

    var jobPositionTitle: String? = null

    var jobPositionDescription: String? = null

    var jobPositionLevel: Int = 0

    var applyDate: String? = null

    var interviewDate: String? = null

    constructor() {}

    constructor(jobPositionId: Long, jobPositionTitle: String,
                jobPositionDescription: String, jobPositionLevel: Int, applyDate: String,
                interviewDate: String) {
        this.jobPositionId = jobPositionId
        this.jobPositionTitle = jobPositionTitle
        this.jobPositionDescription = jobPositionDescription
        this.jobPositionLevel = jobPositionLevel
        this.applyDate = applyDate
        this.interviewDate = interviewDate
    }

}

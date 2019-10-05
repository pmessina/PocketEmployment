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

enum class RecruiterCallState private constructor(val value: Int, val label: String)
{
    INITIAL_CALL(0, "Initial Call"), //Set when user receives a call from recruiter
    RECIEVE_JOB_DESCRIPTION(1, "Receive Job Description"), //Set when user receives job description from specified email
    SEND_RESUME_OR_CONFIRMATION(2, "Send Resume/Confirmation"), //User sends resume to email address mapped to call
    PENDING_INTERVIEW(3, "Pending Interview"), //When set, assign possible actions as user is waiting for an interview
    RECEIVE_INTERVIEW(4, "Receive Interview");


    override fun toString(): String
    {
        return label
    }
}
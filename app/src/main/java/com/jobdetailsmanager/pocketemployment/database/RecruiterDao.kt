package com.jobdetailsmanager.pocketemployment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecruiterDao {

    @Insert
    suspend fun addRecruiter(recruiter: Recruiter)

    @Query("SELECT * from recruiters")
    fun getAllRecruiters() : LiveData<List<Recruiter>>

}
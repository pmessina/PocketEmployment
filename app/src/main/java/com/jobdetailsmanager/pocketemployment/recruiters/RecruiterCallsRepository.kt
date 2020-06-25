package com.jobdetailsmanager.pocketemployment.recruiters

import androidx.lifecycle.LiveData
import com.jobdetailsmanager.pocketemployment.database.Recruiter
import com.jobdetailsmanager.pocketemployment.database.RecruiterCompany
import com.jobdetailsmanager.pocketemployment.database.RecruiterCompanyDao
import com.jobdetailsmanager.pocketemployment.database.RecruiterDao
import kotlinx.coroutines.runBlocking

class RecruiterCallsRepository(private val recruiterCallsDao: RecruiterDao, private val recruiterCompanyDao: RecruiterCompanyDao) {

    fun addRecruiter(phoneNumber: String) {

        //TODO: repeat for update and delete
//        Executors.newSingleThreadExecutor().execute {
//            recruiterCallsDao.addRecruiter(recruiter)
//        }

        runBlocking {

            val recruiterCompany = RecruiterCompany("101 Main St", "www.website.com", "Select Group")
            recruiterCompanyDao.addRecruiterCompany(recruiterCompany)

            //recruiter.recruiterCompanyId = recruiterCompany.recruiterCompanyId

            recruiterCallsDao.addRecruiter(Recruiter("Jane Doe", phoneNumber, "sample@gmail.com", recruiterCompany.recruiterCompanyId))


        }

    }

    fun getRecruiters(): LiveData<List<Recruiter>> {
        return recruiterCallsDao.getAllRecruiters()
    }

    fun getRecruiterCompanies(): LiveData<List<RecruiterCompany>> = recruiterCompanyDao.getAllRecruiterCompanies()

    suspend fun addRecruiterCompanies() = recruiterCompanyDao.addAllRecruiterCompanies()

}
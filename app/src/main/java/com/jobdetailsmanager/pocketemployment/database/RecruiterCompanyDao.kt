package com.jobdetailsmanager.pocketemployment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RecruiterCompanyDao {
    @Insert
    suspend fun addRecruiterCompany(recruiterCompany: RecruiterCompany)

    @Query("SELECT * from recruiter_companies")
    fun getAllRecruiterCompanies() : LiveData<List<RecruiterCompany>>

    @Transaction
    suspend fun  addAllRecruiterCompanies(){
        val rc1 = RecruiterCompany("Select Group", "3340 Peachtree Rd NE suite 1010-1271, Atlanta, GA 30326", "www.selectgroup.com")
//        val r1 = Recruiter("Shelby McNaugton", "9194592515", "shelby@selectgroup.com",  rc1.recruiterCompanyId)
//        val r2 = Recruiter("Katie Northcutt", "9194596421", "knorthcutt@selectgroup.com",  rc1.recruiterCompanyId)


        val rc2 = RecruiterCompany("E-Solutions", "2 North Market Street Suite #400 San Jose, California 95113", "www.e-solutionsinc.com/")
//        val r3 = Recruiter("Tanu Sharma", "", "tanu.s@e-solutionsinc.com",  rc2.recruiterCompanyId)
//        val r4 = Recruiter("Sam Aaron", "917-300-3582", "abhi.samb91@gmail.com",  rc2.recruiterCompanyId)
//        val r5 = Recruiter("Ranjith Kumar", "408-520-9493", "ranjith.eminence@gmail.com",  rc2.recruiterCompanyId)
//        val r6 = Recruiter("Pankaj Singh", "408-722-9419", "pankaj.singh281289@gmail.com",  rc2.recruiterCompanyId)

        //val rcDao = database.recruiterCompanyDao()
        addRecruiterCompany(rc1)
        addRecruiterCompany(rc2)

//        val rDao = database.recruiterDao()
//        rDao.addRecruiter(r1)
//        rDao.addRecruiter(r2)
//        rDao.addRecruiter(r3)
//        rDao.addRecruiter(r4)
//        rDao.addRecruiter(r5)
//        rDao.addRecruiter(r6)
    }
}
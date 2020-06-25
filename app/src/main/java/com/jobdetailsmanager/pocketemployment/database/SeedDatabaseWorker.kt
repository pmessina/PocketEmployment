package com.jobdetailsmanager.pocketemployment.database


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : KoinComponent, CoroutineWorker(context, workerParams) {

    val database: PocketEmploymentDatabase by inject()
    //val rcDao: RecruiterCompanyDao by inject()

    override suspend fun doWork(): Result = coroutineScope {
        try {

            val rc1 = RecruiterCompany("Select Group", "3340 Peachtree Rd NE suite 1010-1271, Atlanta, GA 30326", "www.selectgroup.com")
            val r1 = Recruiter("Shelby McNaugton", "9194592515", "shelby@selectgroup.com",  rc1.recruiterCompanyId)
            val r2 = Recruiter("Katie Northcutt", "9194596421", "knorthcutt@selectgroup.com",  rc1.recruiterCompanyId)


            val rc2 = RecruiterCompany("E-Solutions", "2 North Market Street Suite #400 San Jose, California 95113", "www.e-solutionsinc.com/")
            val r3 = Recruiter("Tanu Sharma", "", "tanu.s@e-solutionsinc.com",  rc2.recruiterCompanyId)
            val r4 = Recruiter("Sam Aaron", "917-300-3582", "abhi.samb91@gmail.com",  rc2.recruiterCompanyId)
            val r5 = Recruiter("Ranjith Kumar", "408-520-9493", "ranjith.eminence@gmail.com",  rc2.recruiterCompanyId)
            val r6 = Recruiter("Pankaj Singh", "408-722-9419", "pankaj.singh281289@gmail.com",  rc2.recruiterCompanyId)

            val rcDao = database.recruiterCompanyDao()
            rcDao.addRecruiterCompany(rc1)
            rcDao.addRecruiterCompany(rc2)

            val rDao = database.recruiterDao()
            rDao.addRecruiter(r1)
            rDao.addRecruiter(r2)
            rDao.addRecruiter(r3)
            rDao.addRecruiter(r4)
            rDao.addRecruiter(r5)
            rDao.addRecruiter(r6)


            Result.success()


        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}

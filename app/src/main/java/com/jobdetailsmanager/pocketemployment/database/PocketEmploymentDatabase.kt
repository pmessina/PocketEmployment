package com.jobdetailsmanager.pocketemployment.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class, Employer::class, Interview::class, JobPosition::class, Recruiter::class, RecruiterCompany::class], version = 3, exportSchema = false)
abstract class PocketEmploymentDatabase : RoomDatabase() {


    abstract fun recruiterDao(): RecruiterDao
    abstract fun recruiterCompanyDao(): RecruiterCompanyDao

//    companion object {
//
//        private var instance: PocketEmploymentDatabase? = null
//
//        fun getInstance(context: Context): PocketEmploymentDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context): PocketEmploymentDatabase {
//
//            return Room.databaseBuilder(context, PocketEmploymentDatabase::class.java, "employment_db")
//                    .addCallback(object : Callback() {
//                        override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//
////                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
////                            WorkManager.getInstance(context).enqueue(request)
//
//                            Executors.newSingleThreadScheduledExecutor().execute {
//                                //getInstance(context).exerciseDao().removeAll();
//
//                            }
//                        }
//
//                    })
//
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//
//        }
//    }
}

//class SeedDatabaseWorker(val context:Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams){
//    override suspend fun doWork(): Result = coroutineScope {
//        try {
//            val database = PocketEmploymentDatabase.getInstance(context)
//            //TODO: Access DAO
//
//            Result.success()
//        }
//        catch(ex:Exception){
//            Result.failure()
//        }
//    }
//}
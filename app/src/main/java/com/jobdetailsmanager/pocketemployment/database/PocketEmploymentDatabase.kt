package com.jobdetailsmanager.pocketemployment.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.util.concurrent.Executors

@Database(entities = [Contact::class, Employer::class, Interview::class, JobPosition::class, Recruiter::class, RecruiterCompany::class], version = 1, exportSchema = false)
abstract class PocketEmploymentDatabase : RoomDatabase() {


    companion object {

        private var instance: PocketEmploymentDatabase? = null

        fun getInstance(context: Context): PocketEmploymentDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PocketEmploymentDatabase {

            return Room.databaseBuilder(context, PocketEmploymentDatabase::class.java, "employment_db")
                    .addCallback(object : Callback() {
                        override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)

                            Executors.newSingleThreadScheduledExecutor().execute {
                                //getInstance(context).exerciseDao().removeAll();
                            }
                        }

                    })

                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()


        }
    }
}

class SeedDatabaseWorker(val context:Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams){
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = PocketEmploymentDatabase.getInstance(context)
            //TODO: Access DAO
            Result.success()
        }
        catch(ex:Exception){
            Result.failure()
        }
    }
}
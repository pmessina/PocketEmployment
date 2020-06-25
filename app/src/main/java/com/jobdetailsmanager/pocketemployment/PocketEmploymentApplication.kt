package com.jobdetailsmanager.pocketemployment

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.jobdetailsmanager.pocketemployment.calls.CallsLogContentProvider
import com.jobdetailsmanager.pocketemployment.database.PocketEmploymentDatabase
import com.jobdetailsmanager.pocketemployment.database.SeedDatabaseWorker
import com.jobdetailsmanager.pocketemployment.interviews.InterviewsStateViewModel
import com.jobdetailsmanager.pocketemployment.recruiters.RecruiterCallsRepository
import com.jobdetailsmanager.pocketemployment.recruiters.RecruiterCallsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
class PocketEmploymentApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PocketEmploymentApplication)
            modules(listOf(employmentModule, roomModule))
            fragmentFactory()
        }


    }


    //Inject Repository in View Model??
    private val employmentModule = module {
        viewModel {
            RecruiterCallsViewModel(get())
        }

        viewModel {
            InterviewsStateViewModel()
        }



    }


    private val roomModule = module {
        single {

            Room.databaseBuilder(get(), PocketEmploymentDatabase::class.java, "employment_db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(this@PocketEmploymentApplication).enqueue(request)
                        }

                    })
                    .fallbackToDestructiveMigration()
                    .build()

        }

//        single {
//            get<RoomDatabase.Builder<PocketEmploymentDatabase>>().addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//
//                    WorkManager.getInstance(this@PocketEmploymentApplication).enqueue(request)
//                }
//
//            })
//
//        }

        single {
            WorkManager.getInstance(get()).enqueue(OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build())
        }

        single {
            get<PocketEmploymentDatabase>().recruiterDao()

        }

        single {
            get<PocketEmploymentDatabase>().recruiterCompanyDao()
        }

        single {
            RecruiterCallsRepository(get(), get())
        }

        single {
            AndroidCalendarProvider(get())
        }

        single{
            CallsLogContentProvider(get())
        }

    }

}
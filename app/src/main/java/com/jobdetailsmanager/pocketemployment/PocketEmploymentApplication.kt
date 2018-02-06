package com.jobdetailsmanager.pocketemployment

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidFragmentScope

class PocketEmploymentApplication : Application(), KodeinAware
{
    override val kodein by Kodein.lazy {

        bind<CallsNotificationManager>() with singleton{CallsNotificationManager(baseContext)}
        bind<GreenDaoHelper>(androidFragmentScope) with singleton{ GreenDaoHelper(baseContext)}

//        bind<CallsNotificationManager>() with factory { context: Context -> CallsNotificationManager(context) }
//        bind<CallsNotificationManager>("app") with provider{ factory<Context, CallsNotificationManager>().invoke(this@PocketEmploymentApplication)}
//        bind<CallsNotificationManager>("instance") with singleton{ instance("app")}
        //import(callsNotificationManager)
    }

    override fun onCreate() {
        super.onCreate()

    }


}
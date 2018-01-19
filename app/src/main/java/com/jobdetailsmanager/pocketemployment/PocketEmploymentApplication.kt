package com.jobdetailsmanager.pocketemployment

import android.app.Application
import io.realm.Realm

class PocketEmploymentApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)


    }
}
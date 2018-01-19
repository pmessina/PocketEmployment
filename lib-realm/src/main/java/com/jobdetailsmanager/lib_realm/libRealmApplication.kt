package com.jobdetailsmanager.lib_realm

import android.app.Application
import io.realm.Realm

class libRealmApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        //Realm.init(this)
    }
}
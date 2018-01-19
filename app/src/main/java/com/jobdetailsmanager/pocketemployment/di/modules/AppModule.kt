package com.jobdetailsmanager.pocketemployment.di.modules

import android.content.Context
import com.jobdetailsmanager.pocketemployment.di.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = arrayOf(
//        register your activities' subcomponents here
))
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: App): Context = application.applicationContext
}
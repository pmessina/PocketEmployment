package com.jobdetailsmanager.pocketemployment.di.components

import com.jobdetailsmanager.pocketemployment.di.App
import com.jobdetailsmanager.pocketemployment.di.factories.ActivitiesInjectorFactories
import com.jobdetailsmanager.pocketemployment.di.factories.FragmentsInjectorFactories
import com.jobdetailsmanager.pocketemployment.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivitiesInjectorFactories::class,
    FragmentsInjectorFactories::class,
    AndroidSupportInjectionModule::class,
    AppModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}
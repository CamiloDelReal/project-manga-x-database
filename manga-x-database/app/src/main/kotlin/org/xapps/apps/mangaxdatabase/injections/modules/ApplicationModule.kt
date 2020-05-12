package org.xapps.apps.mangaxdatabase.injections.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.xapps.apps.mangaxdatabase.injections.utils.InjectingNavHostFragment
import javax.inject.Singleton


@Module(
    includes = [SettingsModule::class, LocalStorageServiceModule::class, FragmentBindingModule::class, ViewModelModule::class]
)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context =
        application.applicationContext

}
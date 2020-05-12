package org.xapps.apps.mangaxdatabase.injections.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.xapps.apps.mangaxdatabase.services.local.*
import javax.inject.Singleton


@Module
class LocalStorageServiceModule {

    companion object {
        private const val DB_FILENAME = "application_database.db"
    }

    @Singleton
    @Provides
    fun provideApplicationDatabase(context: Context): LocalStorageService =
        Room.databaseBuilder(context, LocalStorageService::class.java, DB_FILENAME).build()

    @Singleton
    @Provides
    fun provideTypeDao(service: LocalStorageService): TypeDao =
        service.typeDao()

    @Singleton
    @Provides
    fun provideDemographyDao(service: LocalStorageService): DemographyDao =
        service.demographyDao()

    @Singleton
    @Provides
    fun provideStateDao(service: LocalStorageService): StateDao =
        service.stateDao()

    @Singleton
    @Provides
    fun provideGenreDao(service: LocalStorageService): GenreDao =
        service.genreDao()

    @Singleton
    @Provides
    fun provideRelationshipDao(service: LocalStorageService): RelationshipDao =
        service.relationshipDao()

}
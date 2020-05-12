package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.xapps.apps.mangaxdatabase.services.models.*


@Database(
    entities = [
        Author::class,
        Demography::class,
        Genre::class,
        LandscapePoster::class,
        PortraitPoster::class,
        Reference::class,
        ReferenceAuthor::class,
        ReferenceGenre::class,
        Relationship::class,
        State::class,
        Type::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalStorageService : RoomDatabase() {

    abstract fun typeDao(): TypeDao

    abstract fun demographyDao(): DemographyDao

    abstract fun stateDao(): StateDao

    abstract fun genreDao(): GenreDao

    abstract fun relationshipDao(): RelationshipDao

}
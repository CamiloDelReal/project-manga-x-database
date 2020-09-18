package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.LandscapePoster


@Dao
interface LandscapePosterDao {

    @Insert
    fun insertAsync(poster: LandscapePoster): Single<Long>

    @Insert
    fun insertAsync(posters: List<LandscapePoster>): Single<List<Long>>

    @Insert
    fun insert(poster: LandscapePoster): Long

    @Insert
    fun insert(posters: List<LandscapePoster>): List<Long>

    @Query("SELECT * FROM landscape_posters WHERE id = :id")
    fun posterAsync(id: Long): Single<LandscapePoster>

    @Query("SELECT * FROM landscape_posters")
    fun postersAsync(): Single<List<LandscapePoster>>

    @Query("SELECT * FROM landscape_posters WHERE id = :id")
    fun poster(id: Long): LandscapePoster

    @Query("SELECT * FROM landscape_posters")
    fun posters(): List<LandscapePoster>

}
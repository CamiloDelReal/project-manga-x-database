package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.PortraitPoster


@Dao
interface PortraitPosterDao {

    @Insert
    fun insertAsync(poster: PortraitPoster): Single<Long>

    @Insert
    fun insertAsync(posters: List<PortraitPoster>): Single<List<Long>>

    @Insert
    fun insert(poster: PortraitPoster): Long

    @Insert
    fun insert(posters: List<PortraitPoster>): List<Long>

    @Query("SELECT * FROM portrait_posters WHERE id = :id")
    fun posterAsync(id: Long): Single<PortraitPoster>

    @Query("SELECT * FROM portrait_posters")
    fun postersAsync(): Single<List<PortraitPoster>>

    @Query("SELECT * FROM portrait_posters WHERE id = :id")
    fun poster(id: Long): PortraitPoster

    @Query("SELECT * FROM portrait_posters")
    fun posters(): List<PortraitPoster>

}
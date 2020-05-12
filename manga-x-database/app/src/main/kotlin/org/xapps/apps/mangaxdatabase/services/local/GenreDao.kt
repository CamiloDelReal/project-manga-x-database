package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.Genre


@Dao
interface GenreDao {

    @Insert
    fun insertAsync(genre: Genre): Single<Long>

    @Insert
    fun insertAsync(genres: List<Genre>): Single<List<Long>>

    @Insert
    fun insert(genre: Genre): Long

    @Insert
    fun insert(genres: List<Genre>): List<Long>

    @Query("SELECT * FROM genres WHERE id = :id")
    fun genreAsync(id: Long): Single<Genre>

    @Query("SELECT * FROM genres")
    fun genresAsync(): Single<List<Genre>>

    @Query("SELECT * FROM genres WHERE id = :id")
    fun genre(id: Long): Genre

    @Query("SELECT * FROM genres")
    fun genres(): List<Genre>

}
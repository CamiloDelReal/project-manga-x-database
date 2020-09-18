package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.ReferenceGenre


@Dao
interface ReferenceGenreDao {

    @Insert
    fun insertAsync(referenceGenre: ReferenceGenre): Single<Long>

    @Insert
    fun insertAsync(referencesGenres: List<ReferenceGenre>): Single<List<Long>>

    @Insert
    fun insert(referenceGenre: ReferenceGenre): Long

    @Insert
    fun insert(referencesGenres: List<ReferenceGenre>): List<Long>

    @Query("SELECT * FROM references_genres WHERE reference_id = :referenceId AND genre_id = :genreId")
    fun referenceGenreAsync(referenceId: Long, genreId: Long): Single<ReferenceGenre>

    @Query("SELECT * FROM references_genres WHERE reference_id = :referenceId")
    fun genresByReferenceAsync(referenceId: Long): Single<List<ReferenceGenre>>

    @Query("SELECT * FROM references_genres WHERE genre_id = :genreId")
    fun referencesByGenreAsync(genreId: Long): Single<List<ReferenceGenre>>

    @Query("SELECT * FROM references_genres WHERE reference_id = :referenceId AND genre_id = :genreId")
    fun referenceGenre(referenceId: Long, genreId: Long): ReferenceGenre

    @Query("SELECT * FROM references_genres WHERE reference_id = :referenceId")
    fun genresByReference(referenceId: Long): List<ReferenceGenre>

    @Query("SELECT * FROM references_genres WHERE genre_id = :genreId")
    fun referencesByGenre(genreId: Long): List<ReferenceGenre>

}
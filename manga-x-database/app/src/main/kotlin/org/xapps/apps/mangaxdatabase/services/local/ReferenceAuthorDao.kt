package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.ReferenceAuthor


@Dao
interface ReferenceAuthorDao {

    @Insert
    fun insertAsync(referenceGenre: ReferenceAuthor): Single<Long>

    @Insert
    fun insertAsync(referencesGenres: List<ReferenceAuthor>): Single<List<Long>>

    @Insert
    fun insert(referenceGenre: ReferenceAuthor): Long

    @Insert
    fun insert(referencesGenres: List<ReferenceAuthor>): List<Long>

    @Query("SELECT * FROM references_authors WHERE reference_id = :referenceId AND author_id = :authorId")
    fun referenceAuthorAsync(referenceId: Long, authorId: Long): Single<ReferenceAuthor>

    @Query("SELECT * FROM references_authors WHERE reference_id = :referenceId")
    fun authorsByReferenceAsync(referenceId: Long): Single<List<ReferenceAuthor>>

    @Query("SELECT * FROM references_authors WHERE author_id = :authorId")
    fun referencesByAuthorAsync(authorId: Long): Single<List<ReferenceAuthor>>

    @Query("SELECT * FROM references_authors WHERE reference_id = :referenceId AND author_id = :authorId")
    fun referenceAuthor(referenceId: Long, authorId: Long): ReferenceAuthor

    @Query("SELECT * FROM references_authors WHERE reference_id = :referenceId")
    fun authorsByReference(referenceId: Long): List<ReferenceAuthor>

    @Query("SELECT * FROM references_authors WHERE author_id = :authorId")
    fun referencesByAuthor(authorId: Long): List<ReferenceAuthor>

}
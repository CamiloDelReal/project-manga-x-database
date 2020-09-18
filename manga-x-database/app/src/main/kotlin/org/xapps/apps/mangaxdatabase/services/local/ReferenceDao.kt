package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.Reference


@Dao
interface ReferenceDao {

    @Insert
    fun insertAsync(reference: Reference): Single<Long>

    @Insert
    fun insertAsync(references: List<Reference>): Single<List<Long>>

    @Insert
    fun insert(reference: Reference): Long

    @Insert
    fun insert(references: List<Reference>): List<Long>

    @Query("SELECT * FROM `references` WHERE id = :id")
    fun referenceAsync(id: Long): Single<Reference>

    @Query("SELECT * FROM `references`")
    fun referencesAsync(): Single<List<Reference>>

    @Query("SELECT * FROM `references` WHERE id = :id")
    fun reference(id: Long): Reference

    @Query("SELECT * FROM `references`")
    fun references(): List<Reference>

}
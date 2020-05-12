package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.Demography


@Dao
interface DemographyDao {

    @Insert
    fun insertAsync(demography: Demography): Single<Long>

    @Insert
    fun insertAsync(demographies: List<Demography>): Single<List<Long>>

    @Insert
    fun insert(demography: Demography): Long

    @Insert
    fun insert(demographies: List<Demography>): List<Long>

    @Query("SELECT * FROM demographies WHERE id = :id")
    fun demographyAsync(id: Long): Single<Demography>

    @Query("SELECT * FROM demographies")
    fun demographiesAsync(): Single<List<Demography>>

    @Query("SELECT * FROM demographies WHERE id = :id")
    fun demography(id: Long): Demography

    @Query("SELECT * FROM demographies")
    fun demographies(): List<Demography>

}
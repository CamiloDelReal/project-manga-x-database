package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.Type


@Dao
interface TypeDao {

    @Insert
    fun insertAsync(type: Type): Single<Long>

    @Insert
    fun insertAsync(types: List<Type>): Single<List<Long>>

    @Insert
    fun insert(type: Type): Long

    @Insert
    fun insert(types: List<Type>): List<Long>

    @Query("SELECT * FROM types WHERE id = :id")
    fun typeAsync(id: Long): Single<Type>

    @Query("SELECT * FROM types")
    fun typesAsync(): Single<List<Type>>

    @Query("SELECT * FROM types WHERE id = :id")
    fun type(id: Long): Type

    @Query("SELECT * FROM types")
    fun types(): List<Type>

}
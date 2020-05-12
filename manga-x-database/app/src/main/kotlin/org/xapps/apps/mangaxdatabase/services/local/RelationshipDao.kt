package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.Relationship


@Dao
interface RelationshipDao {

    @Insert
    fun insertAsync(relationship: Relationship): Single<Long>

    @Insert
    fun insertAsync(relationships: List<Relationship>): Single<List<Long>>

    @Insert
    fun insert(relationship: Relationship): Long

    @Insert
    fun insert(relationships: List<Relationship>): List<Long>

    @Query("SELECT * FROM relationships WHERE id = :id")
    fun relationshipAsync(id: Long): Single<Relationship>

    @Query("SELECT * FROM relationships")
    fun relationshipsAsync(): Single<List<Relationship>>

    @Query("SELECT * FROM relationships WHERE id = :id")
    fun relationship(id: Long): Relationship

    @Query("SELECT * FROM relationships")
    fun relationships(): List<Relationship>

}
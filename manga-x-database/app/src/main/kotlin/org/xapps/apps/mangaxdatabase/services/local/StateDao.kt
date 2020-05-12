package org.xapps.apps.mangaxdatabase.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import org.xapps.apps.mangaxdatabase.services.models.State


@Dao
interface StateDao {

    @Insert
    fun insertAsync(state: State): Single<Long>

    @Insert
    fun insertAsync(states: List<State>): Single<List<Long>>

    @Insert
    fun insert(state: State): Long

    @Insert
    fun insert(states: List<State>): List<Long>

    @Query("SELECT * FROM states WHERE id = :id")
    fun stateAsync(id: Long): Single<State>

    @Query("SELECT * FROM states")
    fun statesAsync(): Single<List<State>>

    @Query("SELECT * FROM states WHERE id = :id")
    fun state(id: Long): State

    @Query("SELECT * FROM states")
    fun states(): List<State>

}
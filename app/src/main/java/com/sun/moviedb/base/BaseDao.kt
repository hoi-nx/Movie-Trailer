package com.sun.moviedb.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entities: List<T>)

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)
}

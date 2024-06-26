package io.github.bradpatras.justworkout.database.tag

import androidx.room.*

@Dao
interface TagDao {
    @Upsert
    suspend fun createOrUpdate(vararg tags: TagEntity)

    @Delete
    suspend fun delete(tag: TagEntity)

    @Query("SELECT * FROM tag")
    suspend fun getAll(): List<TagEntity>

    @Update
    suspend fun update(vararg tags: TagEntity)
}
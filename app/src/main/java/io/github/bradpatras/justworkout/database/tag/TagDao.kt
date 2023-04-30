package io.github.bradpatras.justworkout.database.tag

import androidx.room.*

@Dao
interface TagDao {
    @Insert
    fun insert(vararg tags: TagEntity)

    @Delete
    fun delete(tag: TagEntity)

    @Query("SELECT * FROM tag")
    fun getAll(): List<TagEntity>

    @Update
    fun update(vararg tags: TagEntity)
}
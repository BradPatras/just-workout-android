package io.github.bradpatras.justworkout.database.tag

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TagDao {
    @Insert
    fun insertAll(vararg tags: TagEntity)

    @Delete
    fun delete(tag: TagEntity)

    @Query("SELECT * FROM tag")
    fun getAll(): List<TagEntity>

    @Update
    fun update(tag: TagEntity)
}
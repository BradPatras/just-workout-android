package io.github.bradpatras.justworkout.database.workout

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutTagCrossRefDao {
    @Insert
    suspend fun insert(vararg crossRefs: WorkoutTagCrossRef)

    @Delete
    suspend fun delete(crossRef: WorkoutTagCrossRef)

    @Query("SELECT * FROM workout_tag_cross_ref")
    suspend fun getAll(): List<WorkoutTagCrossRef>

    @Update
    suspend fun update(vararg crossRefs: WorkoutTagCrossRef)
}
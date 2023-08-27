package io.github.bradpatras.justworkout.database.workout

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutExerciseCrossRefDao {
    @Insert
    suspend fun insert(vararg crossRefs: WorkoutExerciseCrossRef)

    @Delete
    suspend fun delete(crossRef: WorkoutExerciseCrossRef)

    @Query("SELECT * FROM workout_exercise_cross_ref")
    suspend fun getAll(): List<WorkoutExerciseCrossRef>

    @Update
    suspend fun update(vararg crossRefs: WorkoutExerciseCrossRef)
}
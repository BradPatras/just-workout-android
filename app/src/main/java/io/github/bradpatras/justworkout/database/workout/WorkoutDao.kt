package io.github.bradpatras.justworkout.database.workout

import androidx.room.*

@Dao
interface WorkoutDao {
    @Delete
    suspend fun delete(workout: WorkoutEntity)

    @Transaction
    @Query("SELECT * FROM workout")
    suspend fun getAll(): List<WorkoutWithTagsAndExercises>

    @Update
    suspend fun createOrUpdate(vararg workouts: WorkoutEntity)
}
package io.github.bradpatras.justworkout.database.workout

import androidx.room.*

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(vararg workouts: WorkoutEntity)

    @Delete
    suspend fun delete(workout: WorkoutEntity)

    @Query("SELECT * FROM workout")
    suspend fun getAll(): List<WorkoutEntity>

    @Update
    suspend fun update(vararg workouts: WorkoutEntity)
}
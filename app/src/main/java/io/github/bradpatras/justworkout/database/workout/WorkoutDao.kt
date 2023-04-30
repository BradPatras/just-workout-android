package io.github.bradpatras.justworkout.database.workout

import androidx.room.*

@Dao
interface WorkoutDao {
    @Insert
    fun insert(vararg workout: WorkoutEntity)

    @Delete
    fun delete(workout: WorkoutEntity)

    @Query("SELECT * FROM workout")
    fun getAll(): List<WorkoutEntity>

    @Update
    fun update(vararg workouts: WorkoutEntity)
}
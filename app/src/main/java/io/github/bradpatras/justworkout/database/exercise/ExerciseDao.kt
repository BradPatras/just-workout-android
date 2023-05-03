package io.github.bradpatras.justworkout.database.exercise

import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(vararg exercises: ExerciseEntity)

    @Delete
    suspend fun delete(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<ExerciseEntity>

    @Update
    suspend fun update(vararg exercises: ExerciseEntity)
}
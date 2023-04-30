package io.github.bradpatras.justworkout.database.exercise

import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert
    fun insert(vararg exercise: ExerciseEntity)

    @Delete
    fun delete(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercise")
    fun getAll(): List<ExerciseEntity>

    @Update
    fun update(vararg exercises: ExerciseEntity)
}
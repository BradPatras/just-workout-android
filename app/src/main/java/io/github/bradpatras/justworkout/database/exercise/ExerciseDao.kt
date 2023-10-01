package io.github.bradpatras.justworkout.database.exercise

import androidx.room.*
import java.util.UUID

@Dao
interface ExerciseDao {
    @Upsert
    suspend fun createOrUpdate(vararg exercises: ExerciseEntity)

    @Delete
    suspend fun delete(exercise: ExerciseEntity)

    @Transaction
    @Query("SELECT * FROM exercise WHERE exerciseId = :id")
    suspend fun get(id: UUID): ExerciseWithTags


    @Transaction
    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<ExerciseWithTags>
}
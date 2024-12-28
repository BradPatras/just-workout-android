package io.github.bradpatras.justworkout.database.exercise

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ExerciseDao {
    @Upsert
    suspend fun createOrUpdate(vararg exercises: ExerciseEntity)

    @Delete
    suspend fun delete(exercise: ExerciseEntity)

    @Transaction
    @Query("SELECT * FROM exercise WHERE exerciseId = :id")
    fun get(id: UUID): Flow<ExerciseWithTags>

    @Transaction
    @Query("SELECT * FROM exercise ORDER BY title COLLATE NOCASE ASC")
    fun getAll(): Flow<List<ExerciseWithTags>>
}
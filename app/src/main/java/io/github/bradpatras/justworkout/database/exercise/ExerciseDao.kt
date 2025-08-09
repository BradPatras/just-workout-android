package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
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

    @Query("DELETE FROM exercise WHERE exerciseId in (:ids)")
    fun deleteByIds(ids: List<UUID>)
}
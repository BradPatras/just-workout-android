package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ExerciseRepository {
    @WorkerThread
    fun fetchExercise(id: UUID): Flow<Exercise>

    @WorkerThread
    fun fetchExercises(): Flow<List<Exercise>>

    @WorkerThread
    suspend fun createOrUpdateExercise(exercise: Exercise)

    @WorkerThread
    suspend fun deleteExercise(exercise: Exercise)

    @WorkerThread
    suspend fun deleteExercisesByIds(exerciseIds: List<UUID>)
}
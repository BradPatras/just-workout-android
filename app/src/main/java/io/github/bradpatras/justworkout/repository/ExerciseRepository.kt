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
    fun createOrUpdateExercise(exercise: Exercise): Flow<Unit>

    @WorkerThread
    fun deleteExercise(exercise: Exercise): Flow<Unit>
}
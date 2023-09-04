package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ExerciseRepository {
    @WorkerThread
    fun fetchExercise(
        id: UUID,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Exercise>

    @WorkerThread
    fun fetchExercises(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<Exercise>>

    @WorkerThread
    fun updateExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun deleteExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun createExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>
}
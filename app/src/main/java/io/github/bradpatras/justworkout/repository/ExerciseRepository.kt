package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
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
    )

    @WorkerThread
    fun createExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    )
}
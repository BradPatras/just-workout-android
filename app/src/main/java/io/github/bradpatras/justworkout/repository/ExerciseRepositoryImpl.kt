package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImpl constructor(
    private val exerciseDao: ExerciseDao,
    private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercises(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<Exercise>> {
        TODO("Not yet implemented")
    }

    override fun updateExercise(
        exercise: Exercise, onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun createExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
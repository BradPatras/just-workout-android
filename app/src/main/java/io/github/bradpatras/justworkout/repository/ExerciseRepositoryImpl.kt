package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExerciseRepositoryImpl constructor(
    private val exerciseDao: ExerciseDao,
    private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercises(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<List<Exercise>> {
        exerciseDao
            .getAll()
            .map { it.asExercise() }
    }.flowOn(ioDispatcher)

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
package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseEntity
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

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
            .also { emit(it) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun updateExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        exerciseDao
            .update(exercises = arrayOf(exercise.asExerciseEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        exerciseDao
            .delete(exercise = exercise.asExerciseEntity())
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        exerciseDao
            .insert(exercises = arrayOf(exercise.asExerciseEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}
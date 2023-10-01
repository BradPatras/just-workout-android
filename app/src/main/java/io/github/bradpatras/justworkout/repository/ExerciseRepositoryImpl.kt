package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRef
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRefDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseWithTags
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import java.util.UUID
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseTagCrossRefDao: ExerciseTagCrossRefDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercise(
        id: UUID,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Exercise> {
        exerciseDao
            .get(id)
            .asExercise()
            .also { emit(it) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

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

    override fun createOrUpdateExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .createOrUpdate(exercises = arrayOf(entity.exercise))

        exerciseTagCrossRefDao.deleteByExercise(exercise.id)
        exerciseTagCrossRefDao.insert(
            exercise.tags.map {
                ExerciseTagCrossRef(exerciseId = exercise.id, tagId = it.id)
            }
        )

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .delete(exercise = entity.exercise)

        exerciseTagCrossRefDao
            .deleteByExercise(exerciseId = exercise.id)

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}
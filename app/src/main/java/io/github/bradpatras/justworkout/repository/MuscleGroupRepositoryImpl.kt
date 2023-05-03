package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupDao
import io.github.bradpatras.justworkout.database.musclegroup.asMuscleGroup
import io.github.bradpatras.justworkout.database.musclegroup.asMuscleGroupEntity
import io.github.bradpatras.justworkout.models.MuscleGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class MuscleGroupRepositoryImpl constructor(
    private val muscleGroupDao: MuscleGroupDao,
    private val ioDispatcher: CoroutineDispatcher
): MuscleGroupRepository {
    override fun fetchMuscleGroups(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<List<MuscleGroup>> {
        muscleGroupDao
            .getAll()
            .map { it.asMuscleGroup() }
            .also { emit(it) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun updateMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        muscleGroupDao
            .update(muscleGroups = arrayOf(muscleGroup.asMuscleGroupEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        muscleGroupDao
            .delete(muscleGroup = muscleGroup.asMuscleGroupEntity())
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        muscleGroupDao
            .insert(muscleGroups = arrayOf(muscleGroup.asMuscleGroupEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}
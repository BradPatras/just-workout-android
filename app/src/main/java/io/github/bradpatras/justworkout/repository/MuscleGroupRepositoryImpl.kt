package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupDao
import io.github.bradpatras.justworkout.models.MuscleGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MuscleGroupRepositoryImpl constructor(
    private val muscleGroupDao: MuscleGroupDao,
    private val ioDispatcher: CoroutineDispatcher
): MuscleGroupRepository {
    override fun fetchMuscleGroups(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<MuscleGroup>> {
        TODO("Not yet implemented")
    }

    override fun updateMuscleGroup(
        muscleGroup: MuscleGroup, onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun createMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
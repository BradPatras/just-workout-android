package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    @WorkerThread
    fun fetchTags(): Flow<List<Tag>>

    @WorkerThread
    fun updateTag(
        tag: Tag,
        onComplete: () -> Unit,
        onException: (Exception) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun deleteTag(
        tag: Tag,
        onComplete: () -> Unit,
        onException: (Exception) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun createTag(
        tag: Tag
    ): Flow<Unit>
}
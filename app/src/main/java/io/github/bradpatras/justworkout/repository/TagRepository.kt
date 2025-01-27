package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    @WorkerThread
    fun fetchTags(): Flow<List<Tag>>

    @WorkerThread
    suspend fun updateTag(tag: Tag)

    @WorkerThread
    suspend fun deleteTag(tag: Tag)

    @WorkerThread
    suspend fun createTag(tag: Tag)
}
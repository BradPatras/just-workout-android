package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.models.Tag
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class TagRepositoryImpl constructor(
    private val tagDao: TagDao,
    private val ioDispatcher: CoroutineDispatcher
    ): TagRepository {
    override fun fetchTags(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<Tag>> {
        TODO("Not yet implemented")
    }

    override fun updateTag(
        tag: Tag, onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteTag(
        tag: Tag,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun createTag(
        tag: Tag,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
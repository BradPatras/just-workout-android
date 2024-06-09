package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Tag
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagDao: TagDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): TagRepository {
    override fun fetchTags() = flow<List<Tag>> {
        tagDao
            .getAll()
            .map { it.asTag() }
            .also { emit(it) }
    }
        .flowOn(ioDispatcher)

    override fun updateTag(
        tag: Tag,
        onComplete: () -> Unit,
        onException: (Exception) -> Unit
    ) = flow<Unit> {
        tagDao
            .update(tags = arrayOf(tag.asTagEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteTag(
        tag: Tag,
        onComplete: () -> Unit,
        onException: (Exception) -> Unit
    ) = flow<Unit> {
        tagDao
            .delete(tag = tag.asTagEntity())
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createTag(
        tag: Tag
    ) = flow<Unit> {
        tagDao
            .createOrUpdate(tags = arrayOf(tag.asTagEntity()))
            .also { emit(Unit) }
    }
        .flowOn(ioDispatcher)
}
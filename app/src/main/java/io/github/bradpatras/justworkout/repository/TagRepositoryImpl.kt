package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Tag
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagDao: TagDao,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
): TagRepository {
    override fun fetchTags() = flow<List<Tag>> {
        tagDao
            .getAll()
            .map { it.asTag() }
            .also { emit(it) }
    }
        .flowOn(ioDispatcher)

    override suspend fun updateTag(tag: Tag) {
        tagDao.update(tags = arrayOf(tag.asTagEntity()))
    }

    override suspend fun deleteTag(tag: Tag) {
        tagDao.delete(tag = tag.asTagEntity())
    }

    override suspend fun createTag(tag: Tag) {
        tagDao.createOrUpdate(tags = arrayOf(tag.asTagEntity()))
    }
}
package io.github.bradpatras.justworkout.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey val tagId: UUID,
    val title: String
)

fun TagEntity.asTag() = Tag(
    id = tagId,
    title = title
)

fun Tag.asTagEntity() = TagEntity(
    tagId = id,
    title = title
)
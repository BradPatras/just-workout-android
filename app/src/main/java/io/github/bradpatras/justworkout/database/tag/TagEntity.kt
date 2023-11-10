package io.github.bradpatras.justworkout.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey val title: String
)

fun TagEntity.asTag() = Tag(
    title = title
)

fun Tag.asTagEntity() = TagEntity(
    title = title
)
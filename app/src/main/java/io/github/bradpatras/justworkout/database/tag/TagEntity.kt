package io.github.bradpatras.justworkout.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.Tag

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val color: Int,
    val title: String
)

fun TagEntity.asTag() = Tag(
    color = color,
    id = id,
    title = title
)

fun Tag.asTagEntity() = TagEntity(
    id = id,
    color = color,
    title = title
)

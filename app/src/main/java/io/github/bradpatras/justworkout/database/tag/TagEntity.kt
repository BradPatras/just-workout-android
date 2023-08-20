package io.github.bradpatras.justworkout.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val id: UUID,
    val title: String
)

fun TagEntity.asTag() = Tag(
    id = id,
    title = title
)

fun Tag.asTagEntity() = TagEntity(
    id = id,
    title = title
)
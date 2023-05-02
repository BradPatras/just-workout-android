package io.github.bradpatras.justworkout.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.Tag

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val color: Int
)

fun TagEntity.asTag() = Tag(
    id,
    title,
    color
)

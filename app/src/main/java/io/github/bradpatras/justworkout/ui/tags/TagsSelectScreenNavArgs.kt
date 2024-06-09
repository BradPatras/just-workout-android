package io.github.bradpatras.justworkout.ui.tags

import io.github.bradpatras.justworkout.models.Tag
import java.io.Serializable

data class TagsSelectScreenNavArgs(
    val selectedTags: Array<Tag>
) : Serializable
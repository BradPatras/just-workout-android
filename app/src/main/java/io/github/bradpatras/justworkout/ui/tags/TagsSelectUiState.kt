package io.github.bradpatras.justworkout.ui.tags

import io.github.bradpatras.justworkout.models.Tag

data class TagsSelectUiState(
    val isLoading: Boolean,
    val allTags: List<Tag>,
    val selectedTags: List<Tag>
)

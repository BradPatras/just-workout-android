package io.github.bradpatras.justworkout.ui.tags

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.TagsSelectScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.TagRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagsSelectViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tagRepository: TagRepository
) : ViewModel() {
    private val navArgs: TagsSelectScreenNavArgs = TagsSelectScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        TagsSelectUiState(
            isLoading = false,
            allTags = emptyList(),
            selectedTags = navArgs.selectedTags.toList()
        )
    )
    val uiState: StateFlow<TagsSelectUiState> = _uiState.asStateFlow()

    init {
        fetchTags()
    }

    private fun fetchTags() {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(isLoading = true))

            val tags = tagRepository.fetchTags().first()

            _uiState.emit(
                TagsSelectUiState(
                    isLoading = false,
                    selectedTags = uiState.value.selectedTags,
                    allTags = tags
                )
            )
        }
    }

    fun tagTapped(tag: Tag) {
        val newSelectedTags = uiState.value.selectedTags.toMutableList()

        if (!newSelectedTags.remove(tag)) {
            newSelectedTags.add(tag)
        }

        _uiState.tryEmit(
            uiState.value.copy(
                selectedTags = newSelectedTags
            )
        )
    }

    fun createTagTapped(tagTitle: String) {
        viewModelScope.launch {
            val newTag = Tag(tagTitle)
            tagRepository.createTag(newTag).single()

            _uiState.tryEmit(
                uiState.value.copy(
                    selectedTags = uiState.value.selectedTags + newTag,
                    allTags = uiState.value.allTags + newTag
                )
            )
        }
    }
}
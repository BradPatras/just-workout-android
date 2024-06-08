package io.github.bradpatras.justworkout.ui.tags

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ramcosta.composedestinations.generated.destinations.TagsSelectScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TagsSelectViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: TagsSelectScreenNavArgs = TagsSelectScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        TagsSelectUiState(allTags = emptyList(), selectedTags = navArgs.selectedTags.toList())
    )
    val uiState: StateFlow<TagsSelectUiState> = _uiState.asStateFlow()
}
package io.github.bradpatras.justworkout.ui.workouts.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.WorkoutEditScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutEditViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: WorkoutEditScreenNavArgs = WorkoutEditScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        WorkoutEditUiState(
            id = navArgs.id,
            notes = "",
            isLoading = false,
            exercises = emptyList(),
            title = "",
            tags = emptyList(),
            isNew = navArgs.isNew
        )
    )
    val uiState: StateFlow<WorkoutEditUiState> = _uiState.asStateFlow()

    init {
        if (!navArgs.isNew) {
            viewModelScope.launch {
                workoutRepository.fetchWorkout(id = navArgs.id)
                    .map {
                        _uiState.emit(
                            WorkoutEditUiState(
                                id = it.id,
                                notes = it.notes,
                                isNew = false,
                                isLoading = false,
                                exercises = it.exercises,
                                title = it.title,
                                tags = it.tags
                            )
                        )
                    }
            }
        }
    }

    fun onTitleChanged(title: String) {

    }

    fun onNotesChanged(notes: String) {

    }

    fun onTagsChanged(tags: List<Tag>) {

    }

    fun onExercisesChanged(exercises: List<Tag>) {

    }

}

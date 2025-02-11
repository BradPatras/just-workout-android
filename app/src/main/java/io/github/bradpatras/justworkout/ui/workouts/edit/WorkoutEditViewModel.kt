package io.github.bradpatras.justworkout.ui.workouts.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.WorkoutEditScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
                val workout = workoutRepository.fetchWorkout(id = navArgs.id).first()
                _uiState.emit(
                    WorkoutEditUiState(
                        id = workout.id,
                        notes = workout.notes,
                        isNew = false,
                        isLoading = false,
                        exercises = workout.exercises,
                        title = workout.title,
                        tags = workout.tags
                    )
                )
            }
        }
    }

    fun onTitleChanged(title: String) {
        _uiState.value = uiState.value.copy(
            title = title
        )
    }

    fun onNotesChanged(notes: String) {
        _uiState.value = uiState.value.copy(
            notes = notes
        )
    }

    fun onTagsChanged(tags: List<Tag>) {
        _uiState.value = uiState.value.copy(
            tags = tags
        )
    }

    fun onExercisesChanged(exercises: List<Exercise>) {
        _uiState.value = uiState.value.copy(
            exercises = exercises
        )
    }

    suspend fun onCheckmarkTapped() {
        workoutRepository.createOrUpdateWorkout(
            workout = Workout(
                id = _uiState.value.id,
                title = _uiState.value.title,
                notes = _uiState.value.notes,
                exercises = _uiState.value.exercises,
                tags = _uiState.value.tags,
                datesCompleted = emptyList()
            )
        )
    }

}

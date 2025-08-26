package io.github.bradpatras.justworkout.ui.workouts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

typealias SelectableWorkout = Pair<Workout, Boolean>

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    val workoutRepository: WorkoutRepository,
    tagRepository: TagRepository,
    val uuidProvider: UuidProvider,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val tagFilter = MutableStateFlow<List<Tag>>(emptyList())
    private val isSelectModeEnabled = MutableStateFlow(false)
    private val selectedWorkoutIds = MutableStateFlow<Set<UUID>>(emptySet())

    // Flow that outputs the workouts to be displayed on the list screen based on current filters.
    // This is broken out into a separate flow so that the `getFilteredWorkouts` function only needs
    // to be called when it's specific inputs change.
    val workoutsFlow: StateFlow<List<SelectableWorkout>> = combine(
        workoutRepository.fetchWorkouts(),
        tagFilter.asStateFlow(),
        selectedWorkoutIds.asStateFlow(),
    ) { workouts, tags, selectedWorkoutIds ->
        getFilteredWorkouts(workouts, tags, selectedWorkoutIds)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val uiState: StateFlow<WorkoutListUiState> = combine(
        workoutsFlow,
        tagRepository.fetchTags(),
        tagFilter.asStateFlow(),
        isSelectModeEnabled.asStateFlow(),
    ) { workouts, tags, tagFilter, isSelectModeEnabled ->
        WorkoutListUiState(
            workouts = workouts,
            tagFilter = tagFilter,
            tags = tags,
            isLoading = false,
            isSelectMode = isSelectModeEnabled,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        WorkoutListUiState(isLoading = true)
    )

    fun onTagFilterSelected(tags: List<Tag>) {
        tagFilter.value = tags
    }

    fun onDeleteMenuItemClicked() {
        isSelectModeEnabled.value = true
    }

    fun onDeleteClicked() {
        val idsToDelete = selectedWorkoutIds.value.toList()
        viewModelScope.launch(ioDispatcher) {
            workoutRepository.deleteWorkoutsByIds(idsToDelete)
        }
        isSelectModeEnabled.value = false
        selectedWorkoutIds.value = emptySet()
    }

    fun onWorkoutSelectionChanged(workout: Workout, isSelected: Boolean) {
        if (isSelected) {
            selectedWorkoutIds.value = selectedWorkoutIds.value.plus(workout.id)
        } else {
            selectedWorkoutIds.value = selectedWorkoutIds.value.minus(workout.id)
        }
    }

    fun onCancelClicked() {
        isSelectModeEnabled.value = false
        selectedWorkoutIds.value = emptySet()
    }

    private fun getFilteredWorkouts(
        workouts: List<Workout>,
        tagFilter: List<Tag>,
        selectedWorkoutIds: Set<UUID>,
    ): List<SelectableWorkout> {
        if (tagFilter.isEmpty()) {
            return workouts.map {
                Pair(it, selectedWorkoutIds.contains(it.id))
            }
        }

        return workouts.asSequence()
            .filter { it.tags.isNotEmpty() }
            .map {
                val score = it.tags.intersect(tagFilter.toSet()).size
                Pair(it, score)
            }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map {
                Pair(it.first, selectedWorkoutIds.contains(it.first.id))
            }
            .toList()
    }
}
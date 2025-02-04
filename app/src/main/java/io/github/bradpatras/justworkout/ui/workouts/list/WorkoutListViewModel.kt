package io.github.bradpatras.justworkout.ui.workouts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.ui.exercises.list.ExerciseListUiState
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    workoutRepository: WorkoutRepository,
    tagRepository: TagRepository,
    val uuidProvider: UuidProvider,
): ViewModel() {
    private val tagFilter = MutableStateFlow<List<Tag>>(emptyList())

    val uiState: StateFlow<WorkoutListUiState> = combine(
        workoutRepository.fetchWorkouts(),
        tagRepository.fetchTags(),
        tagFilter.asStateFlow()
    ) { workouts, tags, tagFilter ->
        val filteredWorkouts = getFilteredWorkouts(workouts, tagFilter)
        WorkoutListUiState(
            workouts = filteredWorkouts,
            tagFilter = tagFilter,
            tags = tags,
            isLoading = false
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        WorkoutListUiState(isLoading = true)
    )

    fun onTagFilterSelected(tags: List<Tag>) {
        tagFilter.value = tags
    }

    private fun getFilteredWorkouts(
        workouts: List<Workout>,
        tagFilter: List<Tag>
    ): List<Workout> {
        if (tagFilter.isNotEmpty()) {
            val workoutsWithTags = workouts.filter { it.tags.isNotEmpty() }
            // Calculate scores for each item
            val scoredWorkouts = workoutsWithTags.map { workout ->
                val score = workout.tags.intersect(tagFilter.toSet()).size
                Pair(workout, score)
            }

            // Sort items by score in descending order
            return scoredWorkouts
                .filter { it.second > 0 }
                .sortedByDescending { it.second }
                .map { it.first }
        } else {
            return workouts
        }
    }
}
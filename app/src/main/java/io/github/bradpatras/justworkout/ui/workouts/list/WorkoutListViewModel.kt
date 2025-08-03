package io.github.bradpatras.justworkout.ui.workouts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
        SharingStarted.WhileSubscribed(5000),
        WorkoutListUiState(isLoading = true)
    )

    fun onTagFilterSelected(tags: List<Tag>) {
        tagFilter.value = tags
    }

    private fun getFilteredWorkouts(
        workouts: List<Workout>,
        tagFilter: List<Tag>
    ): List<Workout> {
        if (tagFilter.isEmpty()) {
            return workouts
        }

        return workouts.asSequence()
            .filter { it.tags.isNotEmpty() }
            .map {
                val score = it.tags.intersect(tagFilter.toSet()).size
                Pair(it, score)
            }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { it.first }
            .toList()
    }
}
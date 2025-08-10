package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.SelectableExercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    val exerciseRepository: ExerciseRepository,
    tagRepository: TagRepository,
    val uuidProvider: UuidProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val tagFilter = MutableStateFlow<List<Tag>>(emptyList())
    private val selectedExerciseIds = MutableStateFlow<Set<UUID>>(emptySet())
    private val isSelectModeEnabled = MutableStateFlow(false)

    // Flow that outputs the exercises to be displayed on the list screen based on current filters
    // This is broken out into a separate flow so that the `getFilteredExercises` function only needs
    // to be called when it's specific inputs change.
    val exercisesFlow: Flow<List<SelectableExercise>> = combine(
        exerciseRepository.fetchExercises().distinctUntilChanged(),
        tagFilter.asStateFlow(),
        selectedExerciseIds.asStateFlow(),
    ) { exercises, currentTagFilter, selectedExerciseIds ->
        getFilteredExercises(exercises, currentTagFilter, selectedExerciseIds)
    }

    val uiState: StateFlow<ExerciseListUiState> = combine(
        exercisesFlow,
        tagFilter.asStateFlow(),
        tagRepository.fetchTags().distinctUntilChanged(),
        isSelectModeEnabled,
    ) { exercises, currentTagFilter, tags, isSelectModeEnabled ->
        ExerciseListUiState(
            exercises = exercises,
            tagFilter = currentTagFilter,
            tags = tags,
            isLoading = false,
            isSelectModeEnabled
        )
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ExerciseListUiState(isLoading = true)
        )

    fun onTagFilterSelected(tags: List<Tag>) {
        tagFilter.value = tags
    }

    fun onDeleteClicked() {
        val exerciseIdsToDelete = selectedExerciseIds.value.toList()
        viewModelScope.launch(ioDispatcher) {
            exerciseRepository.deleteExercisesByIds(exerciseIdsToDelete)
        }

        isSelectModeEnabled.value = false
        selectedExerciseIds.value = emptySet()
    }

    fun onDeleteModeClicked() {
        isSelectModeEnabled.value = true
    }

    fun onCancelClicked() {
        isSelectModeEnabled.value = false
        selectedExerciseIds.value = emptySet()
    }

    fun onExerciseSelectionChanged(exerciseId: UUID, isSelected: Boolean) {
        if (isSelected) {
            selectedExerciseIds.value = selectedExerciseIds.value.plus(exerciseId)
        } else {
            selectedExerciseIds.value = selectedExerciseIds.value.minus(exerciseId)
        }
    }

    private fun getFilteredExercises(
        exercises: List<Exercise>,
        tagFilter: List<Tag>,
        selectedExerciseIds: Set<UUID>,
    ): List<SelectableExercise> {
        if (tagFilter.isEmpty()) {
            return exercises.map {
                SelectableExercise(it, selectedExerciseIds.contains(it.id))
            }
        }

        // Use a sequence for lazy evaluation
        return exercises.asSequence()
            .filter { it.tags.isNotEmpty() }
            .map { exercise ->
                val score = exercise.tags.intersect(tagFilter.toSet()).size
                Pair(exercise, score)
            }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { SelectableExercise(it.first, selectedExerciseIds.contains(it.first.id)) }
            .toList()
    }
}
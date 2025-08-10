package io.github.bradpatras.justworkout

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.ui.MainScreen
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var exerciseRepository: ExerciseRepository
    @Inject lateinit var tagRepository: TagRepository
    @Inject lateinit var workoutRepository: WorkoutRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        actionBar?.hide()
        enableEdgeToEdge()

        setContent {
            JustWorkoutTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }

//        lifecycleScope.launch {
//            for (tag in DefaultModels.tags) {
//                tagRepository.createTag(tag)
//            }
//
//            for (exercise in DefaultModels.exercises) {
//                exerciseRepository.createOrUpdateExercise(exercise)
//            }
//
//            for (workout in DefaultModels.workouts) {
//                workoutRepository.createOrUpdateWorkout(workout)
//            }
//        }
    }
}
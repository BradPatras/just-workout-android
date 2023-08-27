package io.github.bradpatras.justworkout.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRefDao
import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRefDao
import io.github.bradpatras.justworkout.database.workout.WorkoutTagCrossRefDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.create(context)
    }

    @Singleton
    @Provides
    fun provideExerciseDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Singleton
    @Provides
    fun provideExerciseTagCrossRefDao(database: AppDatabase): ExerciseTagCrossRefDao {
        return database.exerciseTagCrossRefDao()
    }

    @Singleton
    @Provides
    fun provideTagDao(database: AppDatabase): TagDao {
        return database.tagDao()
    }

    @Singleton
    @Provides
    fun provideWorkoutDao(database: AppDatabase): WorkoutDao {
        return database.workoutDao()
    }

    @Singleton
    @Provides
    fun provideWorkoutExerciseCrossRefDao(database: AppDatabase): WorkoutExerciseCrossRefDao {
        return database.workoutExerciseCrossRefDao()
    }

    @Singleton
    @Provides
    fun provideWorkoutTagCrossRefDao(database: AppDatabase): WorkoutTagCrossRefDao {
        return database.workoutTagCrossRefDao()
    }
}
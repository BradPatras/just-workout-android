package io.github.bradpatras.justworkout.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseEntity
import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupDao
import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupEntity
import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.WorkoutEntity

@Database(
    entities = [
        ExerciseEntity::class,
        MuscleGroupEntity::class,
        TagEntity::class,
        WorkoutEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun muscleGroupDao(): MuscleGroupDao
    abstract fun tagDao(): TagDao
    abstract fun workoutDao(): WorkoutDao
}
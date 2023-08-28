package io.github.bradpatras.justworkout.database.workout

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface WorkoutExerciseCrossRefDao {
    @Insert
    suspend fun insert(crossRefs: List<WorkoutExerciseCrossRef>)

    @Query("DELETE FROM workout_exercise_cross_ref WHERE workoutId = :workoutId")
    suspend fun deleteByWorkout(workoutId: UUID)

    @Query("SELECT * FROM workout_exercise_cross_ref")
    suspend fun getAll(): List<WorkoutExerciseCrossRef>

    @Update
    suspend fun update(vararg crossRefs: WorkoutExerciseCrossRef)
}
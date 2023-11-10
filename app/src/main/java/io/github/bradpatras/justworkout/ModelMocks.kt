package io.github.bradpatras.justworkout

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date
import java.util.UUID

object IncrementalUuid {
    private var i = 0
    private val uuidStrings: List<String> = listOf(
        "ea826c56-4b67-11ee-be56-0242ac120002",
        "ea826ecc-4b67-11ee-be56-0242ac120002",
        "ea826ff8-4b67-11ee-be56-0242ac120002",
        "ea82737c-4b67-11ee-be56-0242ac120002",
        "ea827476-4b67-11ee-be56-0242ac120002",
        "ea827566-4b67-11ee-be56-0242ac120002",
        "ea827656-4b67-11ee-be56-0242ac120002",
        "ea827750-4b67-11ee-be56-0242ac120002",
        "ea827840-4b67-11ee-be56-0242ac120002",
        "ea827926-4b67-11ee-be56-0242ac120002",
        "ea827fa2-4b67-11ee-be56-0242ac120002",
        "ea8281d2-4b67-11ee-be56-0242ac120002",
        "ea8284f2-4b67-11ee-be56-0242ac120002",
        "ea828682-4b67-11ee-be56-0242ac120002",
        "ea8287b8-4b67-11ee-be56-0242ac120002",
        "ea8288d0-4b67-11ee-be56-0242ac120002",
        "ea828baa-4b67-11ee-be56-0242ac120002",
        "ea828cc2-4b67-11ee-be56-0242ac120002",
        "ea828de4-4b67-11ee-be56-0242ac120002",
        "ea828ef2-4b67-11ee-be56-0242ac120002",
        "ea829000-4b67-11ee-be56-0242ac120002",
        "ea82910e-4b67-11ee-be56-0242ac120002",
        "ea82921c-4b67-11ee-be56-0242ac120002",
        "ea82932a-4b67-11ee-be56-0242ac120002",
        "ea8297d0-4b67-11ee-be56-0242ac120002",
        "ea8298f2-4b67-11ee-be56-0242ac120002",
        "ea829a0a-4b67-11ee-be56-0242ac120002",
        "ea829b22-4b67-11ee-be56-0242ac120002",
        "ea829c3a-4b67-11ee-be56-0242ac120002",
        "ea829d48-4b67-11ee-be56-0242ac120002",
        "ea829e74-4b67-11ee-be56-0242ac120002",
        "ea82a2f2-4b67-11ee-be56-0242ac120002",
        "ea82a40a-4b67-11ee-be56-0242ac120002",
        "ea82a4f0-4b67-11ee-be56-0242ac120002",
        "ea82a5d6-4b67-11ee-be56-0242ac120002",
        "ea82a6c6-4b67-11ee-be56-0242ac120002",
        "ea82a7ac-4b67-11ee-be56-0242ac120002",
        "ea82a89c-4b67-11ee-be56-0242ac120002",
        "ea82ab76-4b67-11ee-be56-0242ac120002",
        "ea82ac70-4b67-11ee-be56-0242ac120002",
        "ea82ad56-4b67-11ee-be56-0242ac120002",
        "ea82ae3c-4b67-11ee-be56-0242ac120002",
        "ea82af22-4b67-11ee-be56-0242ac120002",
        "ea82affe-4b67-11ee-be56-0242ac120002",
        "ea82b68e-4b67-11ee-be56-0242ac120002",
        "ea82b800-4b67-11ee-be56-0242ac120002",
        "ea82b8fa-4b67-11ee-be56-0242ac120002",
        "ea82b9d6-4b67-11ee-be56-0242ac120002",
        "ea82babc-4b67-11ee-be56-0242ac120002"
    )

    fun next(): UUID {
        return UUID.fromString(uuidStrings[i]).also {
            i += 1
        }
    }
}
object Mocks {
    val mockTagList1: List<Tag> = listOf(
        Tag(
            title = "Strength"

        ),
        Tag(
            title = "Chest"

        ),
        Tag(
            title = "Arms"

        ),
        Tag(
            title = "Legs"

        ),
        Tag(
            title = "Warmup"

        )
    )

    val mockTagsList2 = listOf(
        Tag(
            title = "Mobility"
        ),
        Tag(
            title = "Warmup"

        )
    )

    val mockExerciseList = listOf(
        Exercise(
            description = "this is the description",
            id = IncrementalUuid.next(),
            tags = mockTagList1,
            title = "Bench pressBench pressBench pressBench pressBench press"

        ),
        Exercise(
            description = "this is the description",
            id = IncrementalUuid.next(),
            tags = mockTagsList2,
            title = "Shoulder circles"
        ),
        Exercise(
            description = "this is the description",
            id = IncrementalUuid.next(),
            tags = mockTagsList2,
            title = "Bicep curls"

        ),
        Exercise(
            description = "this is the description",
            id = IncrementalUuid.next(),
            tags = mockTagList1,
            title = "Chest cable flys"
        )
    )

    val mockWorkoutList = listOf(
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = IncrementalUuid.next(),
                    tags = emptyList(),
                    title = "Bicep curls"

                ),
                Exercise(
                    description = "this is the description",
                    id = IncrementalUuid.next(),
                    tags = emptyList(),
                    title = "Seated Rows"

                )
            ),
            id = IncrementalUuid.next(),
            notes = "these are the notes",
            title = "Basic Pull Workout",
            tags = emptyList()
        ),
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = IncrementalUuid.next(),
                    tags = emptyList(),
                    title = "Lat raises"

                ),
                Exercise(
                    description = "this is the description",
                    id = IncrementalUuid.next(),
                    tags = emptyList(),
                    title = "Bench press"

                )
            ),
            id = IncrementalUuid.next(),
            notes = "these are the notes",
            title = "Basic Push Workout",
            tags = emptyList()
        )
    )
}
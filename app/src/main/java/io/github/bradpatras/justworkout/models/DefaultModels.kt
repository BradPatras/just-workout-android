package io.github.bradpatras.justworkout.models

import java.util.UUID

class DefaultModels {
    companion object {
        val tags: List<Tag> = Tags.entries.map { it.tag }
        val exercises: List<Exercise> = Exercises.entries.map { it.exercise }
        val workouts: List<Workout> = Workouts.entries.map { it.workout }
    }
    
    enum class Tags(val tag: Tag) {
        Core(
            Tag(
                tagId = UUID.fromString("a810a82b-b4f0-44e7-8587-d5aee77c0eab"),
                title = "Core"
            )
        ),
        Legs(
            Tag(
                tagId = UUID.fromString("1b67185a-7278-460e-93ee-5a7a0fab4409"),
                title = "Legs"
            )
        ),
        Pull(
            Tag(
                tagId = UUID.fromString("8b17baaa-2287-4627-8d2b-a13a9580de95"),
                title = "Pull"
            )
        ),
        Push(
            Tag(
                tagId = UUID.fromString("4f7b7fef-ab48-4ee0-a7c5-efacd72672ee"),
                title = "Push"
            )
        ),
        Biceps(
            Tag(
                tagId = UUID.fromString("27fc565a-5ccc-4f51-87e4-547e03b2d895"),
                title = "Biceps"
            )
        ),
        Triceps(
            Tag(
                tagId = UUID.fromString("992d8ce0-2444-4f06-a19e-b4e2a086a6bd"),
                title = "Triceps"
            )
        ),
        Chest(
            Tag(
                tagId = UUID.fromString("d84f8b80-c972-4204-a720-b1a704719b48"),
                title = "Chest"
            )
        ),
        Shoulders(
            Tag(
                tagId = UUID.fromString("0b0a1f02-e019-4f2e-aac1-a3a33f2645fd"),
                title = "Shoulders"
            )
        ),
        Back(
            Tag(
                tagId = UUID.fromString("cc46d7c1-5ddb-4f89-9418-e7b964d44a08"),
                title = "Back"
            )
        )
    }
    
    enum class Exercises(val exercise: Exercise) {
        BACK_SQUAT(
            Exercise(
                description = "",
                id = UUID.fromString("61f67db9-059b-4e1f-97d4-0c6b607b7b02"),
                tags = listOf(Tags.Legs.tag),
                title = "Back Squat"
            )
        ),
        FRONT_SQUAT(
            Exercise(
                description = "",
                id = UUID.fromString("1ce5c734-e2db-4a41-8298-97f0236bb751"),
                tags = listOf(Tags.Legs.tag),
                title = "Front Squat"
            )
        ),
        CALF_RAISE(
            Exercise(
                description = "",
                id = UUID.fromString("2a2617b3-e19f-401e-82e9-23d849eddf36"),
                tags = listOf(Tags.Legs.tag),
                title = "Calf Raise"
            )
        ),
        GOOD_MORNING(
            Exercise(
                description = "",
                id = UUID.fromString("d31a615f-6949-44d2-bed7-c0731273e8bf"),
                tags = listOf(Tags.Legs.tag),
                title = "Good Morning"
            )
        ),
        ROMANIAN_DEADLIFT(
            Exercise(
                description = "",
                id = UUID.fromString("500e000a-8b4b-4a14-a380-3071a0f319cc"),
                tags = listOf(Tags.Legs.tag),
                title = "Romanian Deadlift"
            )
        ),
        BULGARIAN_SPLIT_SQUAT(
            Exercise(
                description = "",
                id = UUID.fromString("1abf338b-feed-4ca1-bd4d-d95aeba7dec5"),
                tags = listOf(Tags.Legs.tag),
                title = "Bulgarian Split Squat"
            )
        ),
        LUNGE(
            Exercise(
                description = "",
                id = UUID.fromString("77f50337-46eb-46f6-9b9a-96b9a3780948"),
                tags = listOf(Tags.Legs.tag),
                title = "Lunge"
            )
        ),
        SINGLE_LEG_ROMANIAN_DEADLIFT(
            Exercise(
                description = "",
                id = UUID.fromString("2fc54d03-811a-4572-b6d6-16ac50b313cc"),
                tags = listOf(Tags.Legs.tag),
                title = "Single-leg Romanian Deadlift"
            )
        ),
        REVERSE_LUNGE(
            Exercise(
                description = "",
                id = UUID.fromString("4a84f2b1-929a-44e5-8df9-cec633f2d69d"),
                tags = listOf(Tags.Legs.tag),
                title = "Reverse Lunge"
            )
        ),
        LATERAL_LUNGE(
            Exercise(
                description = "",
                id = UUID.fromString("ebad4ec9-68f0-48fd-b3ef-b4a4c534d45e"),
                tags = listOf(Tags.Legs.tag),
                title = "Lateral Lunge"
            )
        ),
        DEADLIFT(
            Exercise(
                description = "",
                id = UUID.fromString("2868f37b-0d8b-4ff7-b9f6-a0e29663299b"),
                tags = listOf(Tags.Legs.tag),
                title = "Deadlift"
            )
        ),
        BENCH_PRESS(
            Exercise(
                description = "",
                id = UUID.fromString("3fd12d6e-58c2-409e-a2e9-e5ee89f5b137"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Bench Press"
            )
        ),
        CHEST_FLY(
            Exercise(
                description = "",
                id = UUID.fromString("1b73dd06-2302-4e0c-a7e7-def074ad8a37"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Chest Fly"
            )
        ),
        TRICEP_PULL_DOWN(
            Exercise(
                description = "",
                id = UUID.fromString("eb020225-9392-4f39-a397-2da9383b7977"),
                tags = listOf(Tags.Triceps.tag, Tags.Push.tag),
                title = "Tricep Pull-down"
            )
        ),
        TRICEP_DIP(
            Exercise(
                description = "",
                id = UUID.fromString("71e54df6-aeac-4d27-b96a-b38a249de095"),
                tags = listOf(Tags.Triceps.tag, Tags.Push.tag),
                title = "Tricep Dip"
            )
        ),
        TRICEP_RAISE(
            Exercise(
                description = "",
                id = UUID.fromString("0ef57344-85d5-406c-9622-a95b25aee0f0"),
                tags = listOf(Tags.Triceps.tag, Tags.Push.tag),
                title = "Tricep Raise"
            )
        ),
        INCLINE_BENCH_PRESS(
            Exercise(
                description = "",
                id = UUID.fromString("d8675295-dfb9-490a-80d9-debd99b66c35"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Incline Bench Press"
            )
        ),
        DECLINE_BENCH_PRESS(
            Exercise(
                description = "",
                id = UUID.fromString("0f77b38e-3f8a-4c1c-8a67-d00eae9a56ab"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Decline Bench Press"
            )
        ),
        DUMBBELL_PULLOVER(
            Exercise(
                description = "",
                id = UUID.fromString("34715e3d-7a0e-4b68-86af-b3f8af04854a"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Dumbbell Pullover"
            )
        ),
        TRICEP_KICKBACK(
            Exercise(
                description = "",
                id = UUID.fromString("eadbe333-f4f5-4ffc-93a1-b45ee0cc579c"),
                tags = listOf(Tags.Triceps.tag, Tags.Push.tag),
                title = "Tricep Kickback"
            )
        ),
        PUSH_UP(
            Exercise(
                description = "",
                id = UUID.fromString("e6032189-9c42-47f4-bfe1-68f38041e14d"),
                tags = listOf(Tags.Chest.tag, Tags.Push.tag),
                title = "Push-up"
            )
        ),
        OVERHEAD_PRESS(
            Exercise(
                description = "",
                id = UUID.fromString("4d400b0e-b7c8-4c2e-a9cf-0f92b6d41fbe"),
                tags = listOf(Tags.Shoulders.tag, Tags.Push.tag),
                title = "Overhead Press"
            )
        ),
        LATERAL_RAISE(
            Exercise(
                description = "",
                id = UUID.fromString("af06a5f6-7796-42eb-a8e0-64052b6e8e68"),
                tags = listOf(Tags.Shoulders.tag),
                title = "Lateral Raise"
            )
        ),
        SHRUG(
            Exercise(
                description = "",
                id = UUID.fromString("dc90fb37-ca16-4f60-bf16-20494b638a3a"),
                tags = listOf(Tags.Shoulders.tag),
                title = "Shrug"
            )
        ),
        REVERSE_FLY(
            Exercise(
                description = "",
                id = UUID.fromString("a0a89dfc-5fb0-4a31-b4d0-df6bfd4b4302"),
                tags = listOf(Tags.Back.tag, Tags.Pull.tag),
                title = "Reverse Fly"
            )
        ),
        PULL_UP(
            Exercise(
                description = "",
                id = UUID.fromString("8e615aa2-a052-48c1-9f59-125b7ea9590d"),
                tags = listOf(Tags.Pull.tag, Tags.Back.tag, Tags.Biceps.tag),
                title = "Pull-up"
            )
        ),
        AUSSIE_PULL_UP(
            Exercise(
                description = "",
                id = UUID.fromString("60a42420-4e5a-43ba-8ea4-49fd5d8a0c02"),
                tags = listOf(Tags.Pull.tag, Tags.Back.tag, Tags.Biceps.tag),
                title = "Aussie Pull-up"
            )
        ),
        SEATED_ROW(
            Exercise(
                description = "",
                id = UUID.fromString("3ebbfae7-79ad-4fad-9d19-c7837d288389"),
                tags = listOf(Tags.Back.tag, Tags.Biceps.tag, Tags.Pull.tag),
                title = "Seated Row"
            )
        ),
        PULL_DOWN(
            Exercise(
                description = "",
                id = UUID.fromString("012970fd-d466-40a6-a54c-3f063aaf6305"),
                tags = listOf(Tags.Back.tag, Tags.Biceps.tag, Tags.Pull.tag),
                title = "Pull-down"
            )
        ),
        ONE_ARM_DUMBELL_ROW(
            Exercise(
                description = "",
                id = UUID.fromString("90f52fca-1f0c-43ce-a2bc-6f20296c4813"),
                tags = listOf(Tags.Back.tag, Tags.Biceps.tag, Tags.Pull.tag),
                title = "One-arm Dumbell Row"
            )
        ),
        BACK_EXTENSION(
            Exercise(
                description = "",
                id = UUID.fromString("d7fb2755-77e6-4503-a97a-beb407672329"),
                tags = listOf(Tags.Back.tag, Tags.Legs.tag),
                title = "Back Extension"
            )
        ),
        BENT_OVER_BARBELL_ROW(
            Exercise(
                description = "",
                id = UUID.fromString("fea52b1f-d037-486e-9f36-1f0d32c750a4"),
                tags = listOf(Tags.Back.tag, Tags.Biceps.tag, Tags.Pull.tag),
                title = "Bent-over Barbell Row"
            )
        ),
        HAMSTRING_CURL(
            Exercise(
                description = "",
                id = UUID.fromString("c27d90a0-07d6-489f-a10f-6ebfb54dfe61"),
                tags = listOf(Tags.Legs.tag),
                title = "Hamstring Curl"
            )
        ),
        BICEP_CURL(
            Exercise(
                description = "",
                id = UUID.fromString("f0c95ccc-21f9-41c6-a8ee-96727afd3068"),
                tags = listOf(Tags.Biceps.tag, Tags.Pull.tag),
                title = "Bicep Curl"
            )
        ),
        FOREARM_PLANK(
            Exercise(
                description = "",
                id = UUID.fromString("00aca16b-8ec4-498f-a1e7-7768e061f1de"),
                tags = listOf(Tags.Core.tag),
                title = "Forearm Plank"
            )
        ),
    }

    enum class Workouts(val workout: Workout) {
        PUSH_A(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.BENCH_PRESS.exercise,
                    Exercises.CHEST_FLY.exercise,
                    Exercises.TRICEP_KICKBACK.exercise
                ),
                id = UUID.fromString("21202f18-e309-48c8-a858-71d32edc5e67"),
                title = "Push A",
                tags = listOf(Tags.Push.tag),
                notes = ""
            )
        ),
        PUSH_B(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.INCLINE_BENCH_PRESS.exercise,
                    Exercises.PULL_DOWN.exercise,
                    Exercises.CHEST_FLY.exercise
                ),
                id = UUID.fromString("b6f15a85-bdf3-448e-b4c4-0c0409a4fee5"),
                title = "Push B",
                tags = listOf(Tags.Push.tag),
                notes = ""
            )
        ),
        PULL_A(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.PULL_UP.exercise,
                    Exercises.BICEP_CURL.exercise,
                    Exercises.REVERSE_FLY.exercise,
                ),
                id = UUID.fromString("1440dad4-cf5d-4f85-911c-9424a4b69486"),
                title = "Pull A",
                tags = listOf(Tags.Pull.tag),
                notes = ""
            )
        ),
        PULL_B(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.AUSSIE_PULL_UP.exercise,
                    Exercises.BICEP_CURL.exercise,
                    Exercises.ONE_ARM_DUMBELL_ROW.exercise
                ),
                id = UUID.fromString("ed91ffcc-d89d-4585-8b2d-51fdac95886c"),
                title = "Pull B",
                tags = listOf(Tags.Pull.tag),
                notes = ""
            )
        ),
        LEGS_A(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.DEADLIFT.exercise,
                    Exercises.CALF_RAISE.exercise,
                    Exercises.REVERSE_LUNGE.exercise
                ),
                id = UUID.fromString("cec5a7a0-bd13-4e40-9cf5-d6502ad874f3"),
                title = "Legs A",
                tags = listOf(Tags.Legs.tag),
                notes = ""
            )
        ),
        LEGS_B(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.BACK_SQUAT.exercise,
                    Exercises.CALF_RAISE.exercise,
                    Exercises.HAMSTRING_CURL.exercise
                ),
                id = UUID.fromString("fc9692ac-c3f5-4896-912f-28059e1c9526"),
                title = "Legs B",
                tags = listOf(Tags.Legs.tag),
                notes = ""
            )
        ),
        LEGS_C(
            Workout(
                datesCompleted = emptyList(),
                exercises = listOf(
                    Exercises.BULGARIAN_SPLIT_SQUAT.exercise,
                    Exercises.SINGLE_LEG_ROMANIAN_DEADLIFT.exercise,
                    Exercises.CALF_RAISE.exercise
                ),
                id = UUID.fromString("4898279d-cdf4-4185-8e9a-dc3db3668e9f"),
                title = "Legs C",
                tags = listOf(Tags.Legs.tag),
                notes = ""
            )
        )
    }
}
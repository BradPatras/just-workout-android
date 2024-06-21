package io.github.bradpatras.justworkout

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date
import java.util.UUID

object IncrementalUuid {
    private var i = 0
    private val uuidStrings: List<String> = listOf(
        "5abc0dee-bbb6-4886-b4c9-518a39d361a4",
        "105c477d-3027-42dc-96da-f1f9b8f440fc",
        "06cceea2-4b34-4494-8a01-ad53681c8a60",
        "ade25fa9-9aca-4d96-991a-b0c16076be8a",
        "74b301c1-b4f6-49cc-9048-3d3dc80e4295",
        "cc1b37c8-809e-4d71-a73a-2de0ab7fcea1",
        "393be188-5cf7-49b8-9089-066ba9315fa1",
        "f6fd01e7-55c2-4977-a218-10ffeb94204a",
        "288c5cb7-9251-4988-9e3a-c4161b975f94",
        "78785b65-f8ff-44f1-a0e5-a0a480b16d6a",
        "bd635c94-2f65-40ed-98fe-52c4b37a2275",
        "a2b79931-4bed-4688-9342-945eca7e305f",
        "fdb478b0-1ef5-4523-94ff-ba0a591a153e",
        "9a27e56d-e968-4d18-af01-fb3fe07dbb54",
        "dc06686c-054c-41bf-a360-f2a4da16215b",
        "9c6780df-e6da-44da-8c04-eabb46ddf4a1",
        "4ce15913-3610-4325-80ee-114e8039143b",
        "ea38f75c-289e-4aa7-bdda-378b0c4b16b9",
        "9c8b7aa9-4961-4011-aa96-629966bb5a97",
        "6341eac0-84f7-4af9-84f3-d147513a62af",
        "e235ec8d-6326-4460-abdc-0743b95e2863",
        "92b74e21-e650-4e78-8b38-d868e84977c8",
        "bc8d369e-7700-4e22-bc5c-ccc5a4e962a4",
        "9a4514f5-5d87-4898-8e1c-ce866d6cd6e5",
        "2a88f009-8087-4046-9340-faa3cb3ca4aa",
        "4ec329e0-94a7-4db5-957a-6adbe7e993ff",
        "2f0832e3-bf60-4f4c-a1f0-1964957e1663",
        "d6356827-afb6-4122-ab7d-b332630d56c7",
        "02b24fc6-37e7-4540-978c-4524ebee0e8f",
        "357b53ea-af2d-4735-a933-96386aaf6748",
        "6de0c07d-21ea-426d-9e68-bf82c3aed247",
        "6246259e-5c19-4b51-a7f2-87d27b2ae039",
        "33850783-9dbe-4683-a1a7-2746aea94f1e",
        "7a990c3d-2ab4-4d15-b2ea-48867d440bb5",
        "c17be4db-d072-4eee-b335-da5561735462",
        "eb5a1520-d14f-4411-8668-71f2ae536444",
        "a4a248cb-48bb-4a77-8f39-f25ffc5d4704",
        "156b7000-80d6-403d-936c-3e948bd5d405",
        "0e07b359-446b-4ac7-bc02-db61174c5ea9",
        "6368ebe1-29dc-4ec9-b163-4a8914e54e50",
        "1606fc51-f613-47fd-b1cc-5988841f0850",
        "eade87ca-c7e8-4190-9085-abd850825065",
        "5d876e08-4a08-454b-bb20-32aacafa2ea1",
        "0bcac329-885f-4f57-9c74-88e29e44a581",
        "6f3e6838-86d9-4a3b-9c98-e087574e39ed",
        "167c85dc-1b3d-42dc-9f8b-3b97d4731450",
        "0ff55d9d-0a0a-4cfd-af30-269b2b406e4d",
        "052662b9-5b88-40af-a82e-55e538846e3d",
        "1fc8e0bb-1d9c-4ec2-aa0a-e3205b4a3ea0",
        "d23578a3-28b7-4613-aaff-6b047242b87a",
        "f6d93e6f-b63e-40ed-b045-3c282897755f",
        "a60e4a5c-878a-4d44-9ade-f8082f3dc9b9",
        "d52444b4-2d2c-4ab1-a42f-385302810395",
        "5de6d6d4-0820-4445-9558-8c6a8de64b31",
        "f58993bf-6510-4ad4-8790-87333ceaf81a",
        "97ae57bb-a819-482e-90a2-1b5f439a913b",
        "e93cc1fa-79d3-4e5e-bc0a-432254dc4c03",
        "e123518f-d5a3-49eb-bcae-7ff70a4c5252",
        "f29371e2-159b-48d1-9743-9d7cc4e9ef09",
        "0cce174b-b85c-46a5-a46e-d3e6d5f3722c",
        "e3e96f71-edb1-49ec-8549-a62fd928e07e",
        "7b4fdc79-3e54-4dc4-84f5-cad82aa1e293",
        "09e6fc57-4b65-4e77-a938-dd373f931f67",
        "d0a86520-e96b-4098-9376-099e1a3cba00",
        "295217d4-a783-4b4a-804e-412a7e0846a9",
        "886b6f4b-7e96-4671-ab5e-d4ae30f5e790",
        "a92c224f-dcc9-4fb4-970c-427fc6a39f7c",
        "a447183c-fc66-4cd4-bd4d-a25e8094a665",
        "f3d3d91d-fc6c-4a3e-812f-500c9b650df1",
        "e0f3e463-d753-4c58-8d02-b895f820c4d2",
        "1878a0dd-f90f-4415-8d18-7d80fcd9a037",
        "786251aa-489a-44d3-9ca9-33b669f24a46",
        "19f43dd7-f3e1-4455-86e4-8ac5edd99768",
        "7a1ebadf-a747-44a9-860c-d8f1b5adccad",
        "5d5df6e2-eb1a-4dd0-b0c9-a66599fe2759",
        "173e32df-04bd-45a8-b4a3-17c77af198b7",
        "e921bdd0-8af6-4ffc-957a-e54ad186d448",
        "8ef8870b-b6a0-415e-81e6-d8a1fcab9f0a",
        "a243da95-4a8a-4be3-8570-f97cf474f72b",
        "93dd8589-b8af-4e1c-8e85-84601eb6f547",
        "ba7c3fb1-ada0-4fc4-87d4-1111bdf4c70e",
        "5d13db1e-4bba-4ec9-8570-26bfafaa179f",
        "ab3f08a7-af5d-4c55-bfcd-8cc97efc2c1a",
        "618591e3-4d57-459f-b7c0-d626c6469418",
        "0e99cc61-f81d-43ff-9902-68cf7271b35d",
        "3fa88d22-06d9-436c-83fa-43b80cd4d25c",
        "9cddf935-f0f5-4616-a18b-bf371b059382",
        "cc938993-9fd6-4e4c-8eeb-8ca635533942",
        "36bb2b84-c337-431b-b424-64d0395c1ac7",
        "302a52fe-9430-40f6-9632-ae01f7207137",
        "1ab8eef0-e97a-41cc-beab-b40a5d3f727b",
        "46eca2d7-d62d-41ee-b944-2109a8757e19",
        "4c901997-98e0-477a-b737-c674fd714630",
        "dcf8fab8-86da-4445-86dc-b50fb9cab0e1",
        "e528448b-a984-4ae9-ae2b-1085345b2de4",
        "50ea3167-ee2f-4b87-add2-67e9fbeb225e",
        "f1234b5a-71de-4338-a114-13c9c5179999",
        "3217903e-0ab2-4f93-9f71-85f5357d80bf",
        "a893cbc8-6c48-4573-a4b1-0559322202f3",
        "89fa3422-ab81-4be2-9230-02b19e88129b"
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
            tagId = IncrementalUuid.next(),
            title = "Strength"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Chest"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Arms"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Legs"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Warmup"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Mobility"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Biceps"
        )
    )

    val mockTagsList2 = listOf(
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Running"
        ),
        Tag(
            tagId = IncrementalUuid.next(),
            title = "Knee Rehab"
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
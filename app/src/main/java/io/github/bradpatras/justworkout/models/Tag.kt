package io.github.bradpatras.justworkout.models

import java.io.Serializable
import java.util.UUID

data class Tag(
    val tagId: UUID,
    val title: String
) : Serializable
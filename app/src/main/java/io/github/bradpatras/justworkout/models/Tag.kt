package io.github.bradpatras.justworkout.models

import java.io.Serializable

data class Tag(
    val color: Int,
    val id: Int,
    val title: String
) : Serializable

package io.github.bradpatras.justworkout.utility

import java.util.UUID

class UuidProvider(
    val random: () -> UUID
)
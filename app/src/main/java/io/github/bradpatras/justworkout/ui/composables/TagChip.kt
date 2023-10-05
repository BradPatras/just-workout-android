package io.github.bradpatras.justworkout.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.bradpatras.justworkout.models.Tag

@Composable
fun TagChip(
    title: String,
    onClick: (() -> Unit)? = null
) {
    AssistChip(
        onClick = onClick ?: { },
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = if (onClick == null)
                    MaterialTheme.colorScheme.onTertiaryContainer
                else
                    MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        modifier = Modifier.padding(horizontal = 4.dp),
        border = null,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}
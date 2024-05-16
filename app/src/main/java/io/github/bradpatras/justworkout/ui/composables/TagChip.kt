package io.github.bradpatras.justworkout.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Composable
fun TagChip(
    title: String,
    onClick: (() -> Unit)? = null,
    selected: Boolean
) {
    FilterChip(
        selected = selected,
        onClick = { onClick?.invoke() },
        label = { Text(text = title) },
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors().copy(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        border = null
    )
}

@ExperimentalLayoutApi
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TagChipPreview() {
    JustWorkoutTheme {
        Column {
            FlowRow(
                modifier = Modifier.align(Alignment.Start)
            ) {
                TagChip("first", selected = false)
                TagChip("second", selected = true)
                TagChip("third", selected = false)
                TagChip("fourth", selected = false)
                TagChip("long titled chip example", selected = false)
                TagChip("almost last", selected = true)
                TagChip("last", selected = false)
            }
        }
    }
}
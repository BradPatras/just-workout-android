package io.github.bradpatras.justworkout.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.bradpatras.justworkout.models.Tag

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TagFilterBottomSheet(
    tags: List<Tag>,
    selectedTags: List<Tag>,
    onDismissRequest: () -> Unit,
    onFiltersApplied: (List<Tag>) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var tagSelection by remember { mutableStateOf(selectedTags) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Filter", style = MaterialTheme.typography.headlineSmall)

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                tags.forEach {
                    TagChip(
                        title = it.title,
                        onClick = {
                            tagSelection = tagSelection.toMutableList().apply {
                                if (contains(it)) {
                                    remove(it)
                                } else {
                                    add(it)
                                }
                            }
                        },
                        selected = tagSelection.contains(it)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = { tagSelection = emptyList() }) {
                    Text(text = "Reset", modifier = Modifier.padding(horizontal = 16.dp))

                }

                Button(onClick = {
                    onFiltersApplied(tagSelection)
                    onDismissRequest()
                }) {
                    Text(text = "Apply (${tagSelection.size})", modifier = Modifier.padding(horizontal = 16.dp))
                }
            }

            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}
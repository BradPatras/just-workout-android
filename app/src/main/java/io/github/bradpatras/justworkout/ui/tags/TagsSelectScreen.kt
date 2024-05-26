package io.github.bradpatras.justworkout.ui.tags

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.bottomsheet.spec.DestinationStyleBottomSheet
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.composables.TagChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination<RootGraph>(style = DestinationStyleBottomSheet::class)
fun TagsSelectScreen(destinationsNavigator: DestinationsNavigator) {
    ModalBottomSheet(
        onDismissRequest = {
           destinationsNavigator.navigateUp()
        },
        sheetState = SheetState(
            skipPartiallyExpanded = false,
            density = LocalDensity.current,
            initialValue = SheetValue.Hidden,
        )
    ) {
        TagsSelectContent(
            uiState = TagsSelectUiState(
                allTags = Mocks.mockTagsList2 + Mocks.mockTagList1,
                selectedTags = Mocks.mockTagsList2
            ),
            { }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSelectContent(
    uiState: TagsSelectUiState,
    tagTapped: (Tag) -> Unit
) {
    val selectedTitles = uiState.selectedTags.map { it.title }
    var searchText by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Select Tags",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search or Create") },
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            val tags = uiState.allTags.filter {
                if (searchText.isNotBlank()) {
                    return@filter it.title.lowercase().contains(searchText.lowercase())
                } else {
                    return@filter true
                }
            }.sortedBy {
                it.title
            }

            val searchMatchesTag = tags.map { it.title.lowercase() }.contains(searchText.lowercase().trim())
            if (!searchMatchesTag && searchText.isNotBlank()) {
                TagChip(title = "Create new tag \"$searchText\"", selected = false)
            }

            tags.forEach {
                TagChip(
                    title = it.title,
                    onClick = { tagTapped(it) },
                    selected = selectedTitles.contains(it.title)
                )
            }
        }

        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TagsSelectContentPreview() {
    TagsSelectContent(
        uiState = TagsSelectUiState(
            allTags = Mocks.mockTagList1 + Mocks.mockTagsList2,
            selectedTags = Mocks.mockTagsList2
        ),
        { }
    )
}
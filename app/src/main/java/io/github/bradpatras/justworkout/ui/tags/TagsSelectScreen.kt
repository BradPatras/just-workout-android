package io.github.bradpatras.justworkout.ui.tags

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Composable
@Destination(style = DestinationStyleBottomSheet::class)
fun TagsSelectScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsSelectContent() {
    ModalBottomSheet(
        onDismissRequest = { /*TODO*/ },
        sheetState = SheetState(
            skipPartiallyExpanded = true,
            density = LocalDensity.current,
            initialValue = SheetValue.Expanded,
            skipHiddenState = true
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            "asdflkasjf;lkajsd;lfjasd;lkfjas".forEach {
                Text(text = it.toString(), color = Color.Black)
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TagsSelectContentPreview() {
    TagsSelectContent()
}
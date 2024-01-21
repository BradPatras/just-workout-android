package io.github.bradpatras.justworkout.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Composable
fun TagChip(
    title: String,
    onClick: (() -> Unit)? = null,
    trailingIcon: @Composable () -> Unit = { }
) {
    Chip(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = onClick,
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        },
        labelTextStyle = MaterialTheme.typography.labelLarge,
        labelColor = MaterialTheme.colorScheme.onSurface,
        leadingIcon = { },
        trailingIcon = trailingIcon,
        paddingValues = PaddingValues(8.dp)
    )
}

// Modified version of the Material3 Chip.
// The Material3 chip doesn't make it easy to disable the touch ripple.
@Composable
private fun Chip(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?,
    label: @Composable () -> Unit,
    labelTextStyle: TextStyle,
    labelColor: Color,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    paddingValues: PaddingValues
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 1.dp, vertical = 4.dp)
            .clickable(
                indication = if (onClick == null) null else LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick?.invoke()
            },
        shape = ShapeDefaults.Small,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        ChipContent(
            label = label,
            labelTextStyle = labelTextStyle,
            labelColor = labelColor,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            leadingIconColor = MaterialTheme.colorScheme.primary,
            trailingIconColor = MaterialTheme.colorScheme.primary,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun ChipContent(
    label: @Composable () -> Unit,
    labelTextStyle: TextStyle,
    labelColor: Color,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    leadingIconColor: Color,
    trailingIconColor: Color,
    paddingValues: PaddingValues
) {
    CompositionLocalProvider(
        LocalContentColor provides labelColor,
        LocalTextStyle provides labelTextStyle
    ) {
        Row(
            Modifier
                .defaultMinSize(minHeight = 32.dp)
                .padding(paddingValues),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                CompositionLocalProvider(
                    LocalContentColor provides leadingIconColor, content = leadingIcon
                )
            }
            Spacer(Modifier.width(8.dp))
            label()
            Spacer(Modifier.width(8.dp))
            if (trailingIcon != null) {
                CompositionLocalProvider(
                    LocalContentColor provides trailingIconColor, content = trailingIcon
                )
            }
        }
    }
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
                TagChip("first")
                TagChip("second")
                TagChip("third")
                TagChip(
                    "fourth"
                )
                TagChip("long titled chip example")
                TagChip("almost last", onClick = { })
                TagChip("last")
            }
        }
    }
}
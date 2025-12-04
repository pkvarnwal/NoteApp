package com.example.noteapp.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.R

@Composable
fun TagsInline(tags: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tag),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.width(6.dp))

        tags.forEachIndexed { index, tag ->
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                border = null,
                modifier = Modifier.padding(
                    end = if (index == tags.lastIndex) 0.dp else 8.dp
                )
            )
        }
    }
}


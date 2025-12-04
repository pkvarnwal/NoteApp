package com.example.noteapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TagsWithTitle(
    title: String,
    tags: List<String>,
    selectedTags: List<String>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit
) {
    if (tags.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Tags(
                tags = tags,
                selected = selectedTags,
                onSelect = {
                    onSelect(it)
                }
            )
        }
    }
}

@Composable
fun Tags(
    tags: List<String>,
    selected: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            FilterChip(
                label = { Text(text = tag) },
                shape = RoundedCornerShape(50),
                selected = selected.contains(tag),
                onClick = {
                    onSelect(tag)
                }
            )
        }
    }
}
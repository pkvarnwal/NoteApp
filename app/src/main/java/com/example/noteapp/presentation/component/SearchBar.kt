package com.example.noteapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.ui.theme.White

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                text = "Search notes by title, body, or tags...",
                fontSize = 14.sp
            )
        },
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(color = White)
    )
}
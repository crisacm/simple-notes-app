package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.NoteContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoteBody(
  modifier: Modifier = Modifier,
  lastContent: NoteContent,
  onValueChange: (NoteContent) -> Unit,
) {
  TextField(
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(top = 4.dp, bottom = 4.dp),
    value = lastContent.content.toString(),
    onValueChange = { onValueChange(NoteContent(it)) },
    placeholder = { Text("Tap here to continue") },
    colors =
      TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
      ),
  )
}

@Preview
@Composable
fun NoteBodyPreview() {
  NoteBody(lastContent = NoteContent("This is a note"), onValueChange = {})
}

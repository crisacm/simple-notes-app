package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.ui.theme.GrayDarkerBackground
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoteTitle(
  modifier: Modifier,
  value: String,
  onValueChange: (String) -> Unit,
) {
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .padding(12.dp),
    colors = CardDefaults.cardColors(containerColor = GrayDarkerBackground.copy(alpha = 0.3f)),
    shape = RoundedCornerShape(16.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      TextField(
        modifier = Modifier.weight(1f),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
          Text(
            text = "Note Title",
            fontSize = 24.sp,
            color = Color.LightGray.copy(alpha = 0.5f),
          )
        },
        maxLines = 3,
        textStyle = TextStyle.Default.copy(color = Color.White, fontSize = 24.sp),
        colors =
          TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
          ),
      )
    }
  }
}

@Preview
@Composable
fun NoteTitlePreview() {
  NoteTitle(
    modifier = Modifier,
    value = "Note Title for example, this are a huge title, for this example we are going to use a long title",
    onValueChange = {},
  )
}

package com.github.crisacm.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.crisacm.domain.model.Note
import com.github.crisacm.ui.theme.GrayDarkBackground

@Composable
fun DeleteNoteDialog(
  note: Note,
  onCancel: () -> Unit,
  onDelete: (Note) -> Unit,
) {
  Dialog(onDismissRequest = {}) {
    Card(
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
    ) {
      Column(
        Modifier.background(GrayDarkBackground),
      ) {
        Text(
          text = "Are you sure you want to delete the selected note?",
          textAlign = TextAlign.Center,
          modifier =
            Modifier
              .fillMaxWidth()
              .padding(24.dp),
          fontSize = 16.sp,
          style = MaterialTheme.typography.bodyMedium,
          color = Color.LightGray,
        )
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceAround,
        ) {
          TextButton(onClick = { onCancel() }) {
            Text(
              "Cancel",
              fontWeight = FontWeight.Bold,
              color = Color.LightGray,
              modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            )
          }
          TextButton(onClick = { onDelete(note) }) {
            Text(
              "Delete",
              fontWeight = FontWeight.ExtraBold,
              color = Color.Red.copy(alpha = 0.5f),
              modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            )
          }
        }
      }
    }
  }
}

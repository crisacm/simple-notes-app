package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_mic_google
import org.jetbrains.compose.resources.painterResource

@Composable
fun BodyTypeAudio(
  modifier: Modifier,
  value: String,
  onValueChange: (String) -> Unit,
) {
  if (value.isEmpty()) {
    Box(modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
      Button(
        onClick = {},
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        colors =
          ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
          ),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Icon(
            painterResource(Res.drawable.ic_mic_google),
            contentDescription = "add audio icon",
            modifier =
              Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .size(24.dp),
            tint = Color.LightGray.copy(alpha = 0.5f),
          )
          Text(
            text = "Click to record and audio",
            color = Color.LightGray.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
          )
        }
      }
    }
  }
}

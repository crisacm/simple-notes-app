@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BodyTypeText(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
) {
  Column(modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
    BasicTextField(
      modifier = Modifier.fillMaxWidth(),
      value = value,
      onValueChange = onValueChange,
      textStyle =
        TextStyle(
          color = Color.White.copy(alpha = 0.8f),
          fontSize = 16.sp,
          lineHeight = 22.sp,
        ),
      cursorBrush = SolidColor(Color.White),
      decorationBox = { innerTextField ->
        Row(modifier = Modifier.fillMaxWidth()) {
          if (value.isEmpty()) {
            Text(
              text = "Tap here to continue",
              color = Color.LightGray.copy(alpha = 0.5f),
            )
          }
        }
        innerTextField()
      },
    )
  }
}

package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check_box
import just_notes_kmp.composeapp.generated.resources.ic_imagesmode
import just_notes_kmp.composeapp.generated.resources.ic_mic_google
import just_notes_kmp.composeapp.generated.resources.ic_text_fields
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoteBodyAddContent(
  modifier: Modifier = Modifier,
  onTextClick: () -> Unit,
  onImageClick: () -> Unit,
  onAudioClick: () -> Unit,
  onCheckClick: () -> Unit,
) {
  Column(modifier) {
    Text(
      text = "Add content",
      color = Color.LightGray,
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.padding(horizontal = 12.dp),
    )
    LazyRow(
      modifier = Modifier.padding(12.dp),
    ) {
      item {
        AddContentChip(
          modifier = Modifier.padding(end = 12.dp),
          icon = painterResource(Res.drawable.ic_text_fields),
          iconDesc = "Add text",
          onClick = onTextClick,
        )
        AddContentChip(
          modifier = Modifier.padding(end = 12.dp),
          icon = painterResource(Res.drawable.ic_imagesmode),
          iconDesc = "Add image",
          onClick = onImageClick,
        )
        AddContentChip(
          modifier = Modifier.padding(end = 12.dp),
          icon = painterResource(Res.drawable.ic_check_box),
          iconDesc = "Add checkbox",
          onClick = onCheckClick,
        )
        AddContentChip(
          modifier = Modifier.padding(end = 12.dp),
          icon = painterResource(Res.drawable.ic_mic_google),
          iconDesc = "Record audio",
          onClick = onAudioClick,
        )
      }
    }
  }
}

@Composable
fun AddContentChip(
  modifier: Modifier = Modifier,
  icon: Painter,
  iconDesc: String,
  onClick: () -> Unit,
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(20.dp),
    onClick = onClick,
  ) {
    Box(
      Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
      Icon(
        painter = icon,
        contentDescription = iconDesc,
        tint = GrayLightIcons,
        modifier = Modifier.size(24.dp),
      )
    }
  }
}

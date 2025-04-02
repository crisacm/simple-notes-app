package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.utils.NoteContentType
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_drag_indicator
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoteBody(
  content: NoteContent,
  onValueChange: (NoteContent) -> Unit,
  onRemoveContent: () -> Unit,
) {
  var contentText by remember { mutableStateOf(content.content) }
  Row(
    modifier =
      Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      painter = painterResource(Res.drawable.ic_drag_indicator),
      contentDescription = "delete content icon",
      tint = Color.LightGray.copy(alpha = 0.2f),
      modifier = Modifier.size(24.dp).clickable {},
    )

    Box(
      modifier =
        Modifier
          .weight(1f),
    ) {
      when (content.type) {
        NoteContentType.TEXT -> {
          BodyTypeText(
            modifier = Modifier,
            value = contentText,
            onValueChange = {
              contentText = it
              onValueChange(content.copy(content = contentText))
            },
          )
        }

        NoteContentType.IMAGE -> {
          BodyTypeImage(
            modifier = Modifier,
            value = contentText,
            onValueChange = {
              contentText = it
              onValueChange(content.copy(content = contentText))
            },
          )
        }

        NoteContentType.AUDIO -> {
          BodyTypeAudio(
            modifier = Modifier,
            value = contentText,
            onValueChange = {
              contentText = it
              onValueChange(content.copy(content = contentText))
            },
          )
        }

        NoteContentType.CHECKLIST -> {
          BodyTypeCheck(
            modifier = Modifier,
            checked = content.checked,
            onCheckedChange = {
              onValueChange(content.copy(checked = it))
            },
            value = contentText,
            onValueChange = {
              contentText = it
              onValueChange(content.copy(content = contentText))
            },
          )
        }
      }
    }

    Icon(
      Icons.Default.Close,
      contentDescription = "delete content icon",
      tint = Color.LightGray.copy(alpha = 0.2f),
      modifier = Modifier.size(24.dp).clickable { onRemoveContent() },
    )
  }
}

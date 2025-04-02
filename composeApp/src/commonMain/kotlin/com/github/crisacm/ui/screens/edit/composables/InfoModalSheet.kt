@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.Note
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.utils.DateUtils

@Composable
fun InfoModalSheet(
  visibility: Boolean,
  onVisibilityChange: (Boolean) -> Unit = {},
  note: Note,
) {
  val bottomSheetStateInfo = rememberModalBottomSheetState()
  if (visibility) {
    ModalBottomSheet(
      onDismissRequest = { onVisibilityChange(false) },
      sheetState = bottomSheetStateInfo,
      shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
      InfoContent(note)
    }
  }
}

@Composable
fun InfoContent(note: Note) {
  Column(Modifier.padding(horizontal = 24.dp)) {
    Row(Modifier.fillMaxWidth()) {
      Text(
        text = "Note ID:",
        color = GrayLightIcons,
      )
      Text(
        text = note.id.toString(),
        modifier = Modifier.weight(1f),
        maxLines = 1,
        textAlign = TextAlign.End,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Row(Modifier.fillMaxWidth()) {
      Text(
        text = "Created At:",
        color = GrayLightIcons,
      )
      Text(
        text = DateUtils.convertLongToDate(note.createdAt),
        modifier = Modifier.weight(1f),
        maxLines = 1,
        textAlign = TextAlign.End,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Row(
      modifier =
        Modifier
          .fillMaxWidth()
          .padding(bottom = 24.dp),
    ) {
      Text(
        text = "Last Updated At:",
        color = GrayLightIcons,
      )
      Text(
        text = DateUtils.convertLongToDate(note.updatedAt),
        modifier = Modifier.weight(1f),
        maxLines = 1,
        textAlign = TextAlign.End,
        overflow = TextOverflow.Ellipsis,
      )
    }
  }
}

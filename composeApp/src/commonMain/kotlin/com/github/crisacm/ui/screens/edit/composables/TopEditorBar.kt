package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_redo
import just_notes_kmp.composeapp.generated.resources.ic_undo
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod", "LongParameterList")
@Composable
fun TopEditorBar(
  modifier: Modifier = Modifier,
  onFinishClick: () -> Unit,
  undoEnable: Boolean,
  onUndoClick: () -> Unit,
  redoEnable: Boolean,
  onRedoClick: () -> Unit,
  onDeleteClick: () -> Unit,
) {
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(56.dp),
    shape = RoundedCornerShape(24.dp),
    colors = CardDefaults.cardColors().copy(containerColor = GrayDarkBottomBarBackground),
  ) {
    Row(
      modifier =
        Modifier
          .padding(start = 8.dp, end = 8.dp)
          .height(56.dp)
          .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = onFinishClick),
        imageVector = Icons.Default.Check,
        contentDescription = null,
        tint = GrayLightIcons,
      )
      Spacer(modifier = Modifier.weight(1f))
      Icon(
        painter = painterResource(Res.drawable.ic_undo),
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = {
              if (undoEnable) {
                onUndoClick()
              }
            }),
        contentDescription = null,
        tint = if (undoEnable) GrayLightIcons else GrayLightIcons.copy(alpha = 0.5f),
      )
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = {
              if (redoEnable) {
                onRedoClick()
              }
            }),
        painter = painterResource(Res.drawable.ic_redo),
        contentDescription = null,
        tint = if (redoEnable) GrayLightIcons else GrayLightIcons.copy(alpha = 0.5f),
      )
      Spacer(modifier = Modifier.weight(1f))
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = { onDeleteClick() }),
        imageVector = Icons.Default.Delete,
        contentDescription = null,
        tint = GrayLightIcons,
      )
    }
  }
}

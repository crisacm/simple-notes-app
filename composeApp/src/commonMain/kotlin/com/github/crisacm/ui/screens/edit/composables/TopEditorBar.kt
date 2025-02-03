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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons

@Suppress("LongMethod")
@Composable
fun TopEditorBar(
  modifier: Modifier = Modifier,
  onFinishClick: () -> Unit,
  onUndoClick: () -> Unit,
  onRedoClick: () -> Unit,
  onMenuClick: () -> Unit,
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
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = onUndoClick),
        imageVector = Icons.Default.KeyboardArrowLeft,
        contentDescription = null,
        tint = GrayLightIcons,
      )
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = onRedoClick),
        imageVector = Icons.Default.KeyboardArrowRight,
        contentDescription = null,
        tint = GrayLightIcons,
      )
      Spacer(modifier = Modifier.weight(1f))
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(onClick = onMenuClick),
        imageVector = Icons.Default.MoreVert,
        contentDescription = null,
        tint = GrayLightIcons,
      )
    }
  }
}

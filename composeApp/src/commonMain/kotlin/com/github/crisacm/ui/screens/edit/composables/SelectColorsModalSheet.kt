@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.utils.NoteColors
import com.github.crisacm.ui.composable.RoundedCheckbox
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.NoteBlue
import com.github.crisacm.ui.theme.NoteGreen
import com.github.crisacm.ui.theme.NoteOrange
import com.github.crisacm.ui.theme.NotePurple
import com.github.crisacm.ui.theme.NoteWhite
import com.github.crisacm.ui.theme.NoteYellow
import com.github.crisacm.ui.theme.NoteYellowDark
import kotlinx.coroutines.launch

@Composable
fun SelectColorsModalSheet(
  visibility: Boolean,
  onVisibilityChange: (Boolean) -> Unit = {},
  note: Note,
  onColorSelect: (NoteColors) -> Unit,
) {
  val scope = rememberCoroutineScope()
  val bottomSheetStateInfo = rememberModalBottomSheetState()
  if (visibility) {
    ModalBottomSheet(
      onDismissRequest = { onVisibilityChange(false) },
      sheetState = bottomSheetStateInfo,
      shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
      SelectColorsContent(
        note = note,
        onColorSelect = {
          scope
            .launch { bottomSheetStateInfo.hide() }
            .invokeOnCompletion { onVisibilityChange(false) }
          onColorSelect(it)
        },
      )
    }
  }
}

@Composable
fun SelectColorsContent(
  note: Note,
  onColorSelect: (NoteColors) -> Unit,
) {
  val colors =
    listOf(
      NoteColors.White to NoteWhite,
      NoteColors.Yellow to NoteYellow,
      NoteColors.YellowDark to NoteYellowDark,
      NoteColors.Orange to NoteOrange,
      NoteColors.Purple to NotePurple,
      NoteColors.Green to NoteGreen,
      NoteColors.Blue to NoteBlue,
    )

  Column(Modifier.padding(horizontal = 24.dp)) {
    LazyColumn {
      item {
        Text(
          text = "Select a color",
          style = MaterialTheme.typography.titleLarge,
          color = GrayLightIcons,
          textAlign = TextAlign.Center,
          modifier =
            Modifier
              .fillMaxWidth()
              .padding(bottom = 12.dp),
        )
      }

      items(colors, key = { item -> item.first.name }) { pair ->
        Card(
          modifier =
            Modifier
              .padding(vertical = 4.dp)
              .fillMaxWidth(),
          shape = RoundedCornerShape(20.dp),
          colors =
            CardDefaults.cardColors(
              containerColor = pair.second,
            ),
          onClick = {
            if (note.color != pair.first) {
              onColorSelect(pair.first)
            }
          },
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
          ) {
            RoundedCheckbox(
              modifier = Modifier.padding(start = 12.dp),
              isChecked = note.color == pair.first,
              onValueChange = { onColorSelect(pair.first) },
              uncheckedColor = pair.second.copy(alpha = 0.6f),
            )
            Text(
              text = pair.first.name,
              color = Color.DarkGray,
              textAlign = TextAlign.Center,
              modifier =
                Modifier
                  .weight(1f)
                  .padding(vertical = 12.dp),
            )
          }
        }
      }
    }
  }
}

package com.github.crisacm.ui.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.EditorOption
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.model.defaultEditorOptions
import com.github.crisacm.ui.screens.edit.composables.NoteTitle
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_info
import just_notes_kmp.composeapp.generated.resources.ic_redo
import just_notes_kmp.composeapp.generated.resources.ic_shoppingmode
import just_notes_kmp.composeapp.generated.resources.ic_undo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("LongMethod")
@Composable
fun EditNoteScreen(note: Note) {
  val title = remember { mutableStateOf(note.title) }
  val content = remember { mutableStateOf(note.content) }
  val message = remember { mutableStateOf("") }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopEditorBar(
        onFinishClick = { },
        undoEnable = false,
        onUndoClick = { },
        redoEnable = false,
        onRedoClick = { },
        onMenuClick = { },
      )
    },
  ) { paddingValues ->
    Box(
      modifier =
        Modifier
          .fillMaxSize()
          .background(GrayDarkBackground)
          .padding(paddingValues),
    ) {
      Column(
        modifier =
          Modifier
            .fillMaxSize()
            .background(color = GrayDarkBackground),
      ) {
        Row(
          modifier =
            Modifier
              .fillMaxWidth()
              .padding(start = 16.dp, end = 16.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          SelectTag(
            modifier =
              Modifier
                .weight(1f)
                .padding(top = 6.dp, end = 16.dp, bottom = 6.dp),
            onClick = {},
          )
          Box(
            Modifier
              .padding(top = 6.dp, bottom = 6.dp)
              .clickable(onClick = {}),
          ) {
            Icon(
              modifier = Modifier.size(24.dp),
              painter = painterResource(Res.drawable.ic_info),
              contentDescription = "Info Icon",
              tint = GrayLightIcons,
            )
          }
        }
        LazyColumn(
          modifier =
            Modifier
              .weight(1f)
              .imePadding()
              .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        ) {
          item {
            NoteTitle(
              modifier = Modifier,
              value = title.value,
              onValueChange = { title.value = it },
              onMenuClick = { },
            )
          }
          item {
            TextField(
              modifier =
                Modifier
                  .fillMaxSize()
                  .padding(top = 4.dp, bottom = 4.dp),
              value = message.value,
              onValueChange = { message.value = it },
              placeholder = { Text("Tap here to enter a body") },
              colors =
                TextFieldDefaults.colors(
                  focusedTextColor = Color.White,
                  focusedContainerColor = Color.Transparent,
                  unfocusedContainerColor = Color.Transparent,
                  focusedIndicatorColor = Color.Transparent,
                  unfocusedIndicatorColor = Color.Transparent,
                ),
            )
          }
        }
        BottomEditorOptionsBar(
          modifier = Modifier.imePadding(),
          options = defaultEditorOptions,
          onOptionClick = { },
        )
      }
    }
  }
}

@Composable
fun SelectTag(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Row(
    modifier = modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      modifier = Modifier.size(18.dp),
      painter = painterResource(Res.drawable.ic_shoppingmode),
      contentDescription = "Tag Icon",
      tint = GrayLightIcons,
    )
    Text(
      modifier = Modifier.padding(start = 6.dp),
      text = "Tag",
      color = GrayLightIcons,
    )
    Icon(
      imageVector = Icons.Default.ArrowDropDown,
      contentDescription = "Select tag",
      tint = GrayLightIcons,
    )
  }
}

@Composable
fun BottomEditorOptionsBar(
  modifier: Modifier = Modifier,
  options: List<EditorOption>,
  onOptionClick: (EditorOption) -> Unit,
) {
  LazyRow(
    modifier =
      modifier
        .fillMaxWidth()
        .padding(16.dp),
  ) {
    options.forEachIndexed { index, _ ->
      item {
        Card(
          modifier = Modifier,
          shape =
            RoundedCornerShape(
              topStart = if (index == 0) 25.dp else 0.dp,
              bottomStart = if (index == 0) 25.dp else 0.dp,
              topEnd = if (index == options.size - 1) 25.dp else 0.dp,
              bottomEnd = if (index == options.size - 1) 25.dp else 0.dp,
            ),
          colors = CardDefaults.cardColors().copy(containerColor = GrayDarkBottomBarBackground),
        ) {
          IconButton(
            modifier =
              Modifier
                .padding(
                  start = if (index == 0) 6.dp else 0.dp,
                  end = 6.dp,
                ),
            onClick = { onOptionClick(options[index]) },
          ) {
            Icon(
              modifier = Modifier.size(24.dp),
              painter = painterResource(options[index].icon),
              contentDescription = options[index].desc,
              tint = GrayLightIcons,
            )
          }
        }
      }
    }
  }
}

@Suppress("LongMethod", "LongParameterList")
@Composable
fun TopEditorBar(
  modifier: Modifier = Modifier,
  onFinishClick: () -> Unit,
  undoEnable: Boolean,
  onUndoClick: () -> Unit,
  redoEnable: Boolean,
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
            .clickable(onClick = onMenuClick),
        imageVector = Icons.Default.MoreVert,
        contentDescription = null,
        tint = GrayLightIcons,
      )
    }
  }
}

@Preview
@Composable
fun TopEditorBarPreview() {
  TopEditorBar(
    onFinishClick = { },
    undoEnable = true,
    onUndoClick = { },
    redoEnable = false,
    onRedoClick = { },
    onMenuClick = { },
  )
}

@Preview
@Composable
fun BottomEditorOptionsBarPreview() {
  BottomEditorOptionsBar(
    options = defaultEditorOptions,
    onOptionClick = {},
  )
}

@Preview
@Composable
fun SelectTagPreview() {
  SelectTag(onClick = {})
}

@Preview
@Composable
fun EditNoteScreenPreview() {
  EditNoteScreen(
    note =
      Note(
        title = "",
        content =
          listOf(
            NoteContent(
              content = "Note Content",
            ),
          ),
      ),
  )
}

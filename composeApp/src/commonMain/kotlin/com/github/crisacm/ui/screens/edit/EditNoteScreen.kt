@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.edit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.utils.NoteColors
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.base.SIDE_EFFECTS_KEY
import com.github.crisacm.ui.composable.DeleteNoteDialog
import com.github.crisacm.ui.screens.edit.composables.BottomEditorOptionsBar
import com.github.crisacm.ui.screens.edit.composables.EditorOption
import com.github.crisacm.ui.screens.edit.composables.EditorOptionType
import com.github.crisacm.ui.screens.edit.composables.InfoModalSheet
import com.github.crisacm.ui.screens.edit.composables.NoteBody
import com.github.crisacm.ui.screens.edit.composables.NoteTitle
import com.github.crisacm.ui.screens.edit.composables.SelectColorsModalSheet
import com.github.crisacm.ui.screens.edit.composables.SelectTag
import com.github.crisacm.ui.screens.edit.composables.SelectTagModalSheet
import com.github.crisacm.ui.screens.edit.composables.TopEditorBar
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkerBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.NoteBlue
import com.github.crisacm.ui.theme.NoteGreen
import com.github.crisacm.ui.theme.NoteOrange
import com.github.crisacm.ui.theme.NotePurple
import com.github.crisacm.ui.theme.NoteWhite
import com.github.crisacm.ui.theme.NoteYellow
import com.github.crisacm.ui.theme.NoteYellowDark
import com.github.crisacm.ui.theme.RedLight
import com.github.crisacm.ui.theme.RedMatte
import com.github.crisacm.utils.AppLogger
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check_box
import just_notes_kmp.composeapp.generated.resources.ic_imagesmode
import just_notes_kmp.composeapp.generated.resources.ic_info
import just_notes_kmp.composeapp.generated.resources.ic_mic_google
import just_notes_kmp.composeapp.generated.resources.ic_palette
import just_notes_kmp.composeapp.generated.resources.ic_text_fields
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun EditNoteScreen(
  id: Long,
  state: EditNoteContracts.State,
  effect: Flow<EditNoteContracts.Effect>?,
  onEventSent: (event: EditNoteContracts.Event) -> Unit,
  onNavigationRequested: (effect: EditNoteContracts.Effect.Navigation) -> Unit,
) {
  AppLogger.d("EditNoteScreen", "state: $state")

  var noteTitle by remember { mutableStateOf("") }
  var noteContent by remember { mutableStateOf(emptyList<NoteContent>()) }
  var noteColor by remember { mutableStateOf(NoteWhite) }

  var showSheetTags by remember { mutableStateOf(false) }
  var showSheetInfo by remember { mutableStateOf(false) }
  var showSheetColors by remember { mutableStateOf(false) }

  var deleteDialogVisibility by remember { mutableStateOf(false) }
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(SIDE_EFFECTS_KEY) {
    effect?.collect { effect ->
      when (effect) {
        is EditNoteContracts.Effect.Navigation.ToHome -> onNavigationRequested(effect)
        is EditNoteContracts.Effect.ShowError -> snackbarHostState.showSnackbar(effect.message)
      }
    }
  }

  LaunchedEffect(Unit) {
    if (state.note == null) {
      onEventSent(EditNoteContracts.Event.LoadNoteInfo(id))
    }
  }

  LaunchedEffect(state.note) {
    noteTitle = state.note?.title ?: ""
    noteColor =
      when (state.note?.color) {
        NoteColors.White -> NoteWhite
        NoteColors.Yellow -> NoteYellow
        NoteColors.YellowDark -> NoteYellowDark
        NoteColors.Orange -> NoteOrange
        NoteColors.Purple -> NotePurple
        NoteColors.Green -> NoteGreen
        NoteColors.Blue -> NoteBlue
        else -> NoteWhite
      }
  }

  LaunchedEffect(state.noteContent) {
    noteContent = state.noteContent
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
      if (!state.isLoading && state.note != null) {
        TopEditorBar(
          onFinishClick = { onEventSent(EditNoteContracts.Event.GoBack) },
          undoEnable = false,
          onUndoClick = { },
          redoEnable = false,
          onRedoClick = { },
          onDeleteClick = { deleteDialogVisibility = true },
        )
      }
    },
    bottomBar = {
      BottomEditorOptionsBar(
        modifier = Modifier.imePadding(),
        options =
          listOf(
            EditorOption(
              desc = "Add Text Content",
              icon = Res.drawable.ic_text_fields,
              type = EditorOptionType.ADD_TEXT,
            ),
            EditorOption(
              desc = "Add Image Content",
              icon = Res.drawable.ic_imagesmode,
              type = EditorOptionType.ADD_IMAGE,
            ),
            EditorOption(
              desc = "Add Checklist Content",
              icon = Res.drawable.ic_check_box,
              type = EditorOptionType.ADD_CHECKLIST,
            ),
            EditorOption(
              desc = "Add Audio Content",
              icon = Res.drawable.ic_mic_google,
              type = EditorOptionType.ADD_AUDIO,
            ),
          ),
        onOptionClick = {
          if (it.type == EditorOptionType.ADD_TEXT) {
            onEventSent(EditNoteContracts.Event.AddContent(NoteContentType.TEXT))
          }

          if (it.type == EditorOptionType.ADD_IMAGE) {
            onEventSent(EditNoteContracts.Event.AddContent(NoteContentType.IMAGE))
          }

          if (it.type == EditorOptionType.ADD_CHECKLIST) {
            onEventSent(EditNoteContracts.Event.AddContent(NoteContentType.CHECKLIST))
          }

          if (it.type == EditorOptionType.ADD_AUDIO) {
            onEventSent(EditNoteContracts.Event.AddContent(NoteContentType.AUDIO))
          }
        },
      )
    },
  ) { paddingValues ->
    if (!state.isLoading && state.error == null && state.note != null) {
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
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            SelectTag(
              tag = state.noteTag,
              onClick = { showSheetTags = true },
            )
            Spacer(Modifier.weight(1f))
            Card(
              modifier = Modifier,
              shape = CircleShape,
              border = BorderStroke(2.dp, GrayLightIcons),
              colors = CardDefaults.cardColors(containerColor = noteColor),
              content = { Box(modifier = Modifier.size(24.dp)) },
            )
            Card(
              modifier = Modifier.padding(end = 12.dp),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
              IconButton(onClick = { showSheetColors = true }) {
                Icon(
                  modifier = Modifier.size(24.dp),
                  painter = painterResource(Res.drawable.ic_palette),
                  contentDescription = "Info Icon",
                  tint = GrayLightIcons,
                )
              }
            }
            Card(
              modifier = Modifier.padding(end = 24.dp),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
              IconButton(onClick = { showSheetInfo = true }) {
                Icon(
                  modifier = Modifier.size(24.dp),
                  painter = painterResource(Res.drawable.ic_info),
                  contentDescription = "Info Icon",
                  tint = GrayLightIcons,
                )
              }
            }
          }
          LazyColumn(
            modifier =
              Modifier
                .weight(1f)
                .imePadding(),
          ) {
            item {
              NoteTitle(
                modifier = Modifier.padding(horizontal = 12.dp),
                value = noteTitle,
                onValueChange = {
                  noteTitle = it
                  onEventSent(EditNoteContracts.Event.UpdateNote { copy(title = it) })
                },
              )
            }

            // Load here the content
            if (noteContent.isEmpty()) {
              item {
                StateNoteContentEmpty()
              }
            } else {
              items(noteContent, key = { item -> "${item.id}-${item.createdAt}" }) { item ->
                Box(modifier = Modifier.fillMaxWidth()) {
                  NoteBody(
                    content = item,
                    onValueChange = { content ->
                      onEventSent(EditNoteContracts.Event.UpdateContent(content))
                    },
                    onRemoveContent = {
                      onEventSent(EditNoteContracts.Event.RemoveContent(item))
                    },
                  )
                }
              }
            }
          }
        }
      }

      if (deleteDialogVisibility) {
        DeleteNoteDialog(
          note = state.note,
          onCancel = { deleteDialogVisibility = false },
          onDelete = {
            deleteDialogVisibility = false
            onEventSent(EditNoteContracts.Event.DeleteNote(it))
          },
        )
      }

      SelectTagModalSheet(
        visibility = showSheetTags,
        onVisibilityChange = { showSheetTags = it },
        tags = state.tags,
        selectedTag = state.noteTag,
        onSelectTag = { onEventSent(EditNoteContracts.Event.UpdateTag(it)) },
        onCreateTag = { onEventSent(EditNoteContracts.Event.CreateTag(it)) },
        onRemoveTag = { onEventSent(EditNoteContracts.Event.RemoveTag) },
        onDeleteTag = { onEventSent(EditNoteContracts.Event.DeleteTag(it)) },
      )

      SelectColorsModalSheet(
        visibility = showSheetColors,
        onVisibilityChange = { showSheetColors = it },
        note = state.note,
        onColorSelect = { color ->
          onEventSent(EditNoteContracts.Event.UpdateNote { copy(color = color) })
        },
      )

      InfoModalSheet(
        visibility = showSheetInfo,
        onVisibilityChange = { showSheetInfo = it },
        state.note,
      )
    }

    if (state.isLoading && state.error == null) {
      StateLoadingContent(Modifier.padding(paddingValues))
    }

    if (!state.isLoading && state.error != null) {
      StateErrorContent(
        modifier = Modifier.padding(paddingValues),
        error = state.error,
        onGoBack = { onEventSent(EditNoteContracts.Event.GoBack) },
      )
    }
  }
}

@Composable
fun StateNoteContentEmpty() {
  Card(
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp),
    colors = CardDefaults.cardColors(containerColor = GrayDarkerBackground.copy(alpha = 0.3f)),
    shape = RoundedCornerShape(16.dp),
  ) {
    Text(
      text = "Not are content",
      modifier =
        Modifier
          .fillMaxWidth()
          .padding(12.dp),
      textAlign = TextAlign.Center,
      color = Color.White.copy(alpha = 0.7f),
    )
  }
}

@Composable
fun StateLoadingContent(modifier: Modifier) {
  Box(
    modifier =
      modifier
        .fillMaxSize()
        .background(GrayDarkBackground),
    contentAlignment = Alignment.Center,
  ) {
    CircularProgressIndicator(
      modifier = Modifier.size(24.dp),
      strokeWidth = 1.dp,
      color = Color.White,
    )
  }
}

@Composable
fun StateErrorContent(
  modifier: Modifier,
  error: String,
  onGoBack: () -> Unit,
) {
  Column(
    modifier =
      modifier
        .fillMaxSize()
        .background(GrayDarkBackground),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Icon(
      Icons.Default.Info,
      modifier = Modifier.size(48.dp),
      contentDescription = "Info Icon",
      tint = RedLight,
    )
    Text(
      text = error,
      modifier = Modifier.padding(16.dp),
      color = Color.LightGray,
      fontSize = 20.sp,
      fontWeight = FontWeight.SemiBold,
    )
    Button(
      onClick = { onGoBack() },
      colors = ButtonDefaults.buttonColors(containerColor = RedMatte),
    ) {
      Text(text = "Go Back")
    }
  }
}

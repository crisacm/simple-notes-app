package com.github.crisacm.ui.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.crisacm.domain.model.Note
import com.github.crisacm.ui.screens.edit.composables.TopEditorBar
import com.github.crisacm.ui.theme.GrayDarkBackground

@Composable
fun EditNoteScreen(note: Note) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopEditorBar(
        onFinishClick = { },
        onUndoClick = { },
        onRedoClick = { },
        onMenuClick = { },
      )
    },
  ) { paddingValues ->
    Column(
      modifier = Modifier.fillMaxSize().background(color = GrayDarkBackground),
    ) {
    }
  }
}

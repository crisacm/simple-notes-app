package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.domain.model.Note
import com.github.crisacm.ui.screens.edit.EditNoteScreen

@Composable
fun EditNoteDestination(
  navController: NavController,
  noteId: String,
) {
  EditNoteScreen(Note())
}

@file:OptIn(KoinExperimentalAPI::class)

package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.ui.screens.edit.EditNoteContracts
import com.github.crisacm.ui.screens.edit.EditNoteScreen
import com.github.crisacm.ui.screens.edit.EditNoteViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun EditNoteDestination(
  navController: NavController,
  noteId: Long,
) {
  val viewModel = koinViewModel<EditNoteViewModel>()
  EditNoteScreen(
    id = noteId,
    state = viewModel.viewState.value,
    effect = viewModel.effect,
    onEventSent = viewModel::handleEvent,
    onNavigationRequested = { navigationEffect ->
      if (navigationEffect is EditNoteContracts.Effect.Navigation.ToHome) {
        navController.navigateUp()
      }
    },
  )
}

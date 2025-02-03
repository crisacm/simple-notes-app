package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.ui.navigation.EditNote
import com.github.crisacm.ui.screens.home.HomeScreen

@Composable
fun HomeDestinationScreen(navController: NavController) {
  HomeScreen(
    navigateToEditNote = { noteId ->
      navController.navigate(EditNote(noteId))
    },
  )
}

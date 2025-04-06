@file:OptIn(KoinExperimentalAPI::class)

package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.ui.navigation.EditNote
import com.github.crisacm.ui.screens.home.HomeContracts
import com.github.crisacm.ui.screens.home.HomeScreen
import com.github.crisacm.ui.screens.home.HomeViewModel
import com.github.crisacm.utils.WebBrowser
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun homeDestinationScreen(
  navController: NavController,
  webBrowser: WebBrowser,
) {
  val viewModel = koinViewModel<HomeViewModel>()
  HomeScreen(
    state = viewModel.viewState.value,
    effect = viewModel.effect,
    onEventSent = viewModel::handleEvent,
    navigateTo = { effect ->
      when (effect) {
        is HomeContracts.Effect.Navigation.ToEdit -> {
          navController.navigate(EditNote(effect.id))
        }

        HomeContracts.Effect.Navigation.ToGitHub -> {
          webBrowser.openUrl("https://github.com/crisacm")
        }
      }
    },
  )
}

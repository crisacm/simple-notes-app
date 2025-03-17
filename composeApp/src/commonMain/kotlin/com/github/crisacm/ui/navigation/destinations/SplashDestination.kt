@file:OptIn(KoinExperimentalAPI::class)

package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.ui.navigation.Home
import com.github.crisacm.ui.navigation.Login
import com.github.crisacm.ui.screens.splash.SplashScreen
import com.github.crisacm.ui.screens.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun splashDestinationScreen(navController: NavController) {
  val viewModel = koinViewModel<SplashViewModel>()
  SplashScreen(
    state = viewModel.viewState.value,
    effect = viewModel.effect,
    onSentEvent = viewModel::handleEvent,
    onNavigateTo = {
      navController.popBackStack()
      when (it) {
        Home -> navController.navigate(Home)
        Login -> navController.navigate(Login)
      }
    },
  )
}

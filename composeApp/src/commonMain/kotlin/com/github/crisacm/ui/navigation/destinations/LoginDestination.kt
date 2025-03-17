@file:OptIn(KoinExperimentalAPI::class)

package com.github.crisacm.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.crisacm.ui.navigation.Home
import com.github.crisacm.ui.screens.login.LoginViewModel
import com.github.crisacm.ui.screens.login.loginScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun loginDestinationScreen(navController: NavController) {
  val viewModel = koinViewModel<LoginViewModel>()
  loginScreen(
    onNavigateToHome = { navController.navigate(Home) },
    viewModel = viewModel,
  )
}

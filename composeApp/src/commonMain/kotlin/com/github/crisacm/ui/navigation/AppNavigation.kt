package com.github.crisacm.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.crisacm.ui.navigation.destinations.EditNoteDestination
import com.github.crisacm.ui.navigation.destinations.homeDestinationScreen
import com.github.crisacm.utils.WebBrowser

private const val TRANSITION_DURATION = 500

@Composable
fun AppNavigation(
  startDestination: Any = Home,
  webBrowser: WebBrowser,
) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = startDestination,
    enterTransition = {
      slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(TRANSITION_DURATION),
      )
    },
    exitTransition = {
      slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(TRANSITION_DURATION),
      )
    },
    popEnterTransition = {
      slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(TRANSITION_DURATION),
      )
    },
    popExitTransition = {
      slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(TRANSITION_DURATION),
      )
    },
  ) {
    composable<Home> {
      homeDestinationScreen(navController, webBrowser)
    }

    composable<EditNote> {
      val args = it.toRoute<EditNote>()
      EditNoteDestination(navController, args.noteId)
    }
  }
}

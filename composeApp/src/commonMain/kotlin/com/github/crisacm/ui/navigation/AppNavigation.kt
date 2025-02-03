package com.github.crisacm.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.crisacm.ui.navigation.destinations.HomeDestinationScreen
import com.github.crisacm.ui.navigation.destinations.EditNoteDestination

const val TRANSITION_DURATION = 300

@Composable
fun AppNavigation(startDestination: Any) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = startDestination,
    enterTransition = {
      fadeIn(
        animationSpec = tween(TRANSITION_DURATION),
      ) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(TRANSITION_DURATION))
    },
    exitTransition = {
      fadeOut(
        animationSpec = tween(TRANSITION_DURATION),
      ) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(TRANSITION_DURATION))
    },
  ) {
    composable<Home> {
      HomeDestinationScreen(navController)
    }

    composable<EditNote> {
      EditNoteDestination(navController, "")
    }
  }
}

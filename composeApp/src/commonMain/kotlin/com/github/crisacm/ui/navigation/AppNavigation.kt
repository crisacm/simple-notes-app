package com.github.crisacm.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.crisacm.ui.navigation.destinations.EditNoteDestination
import com.github.crisacm.ui.navigation.destinations.homeDestinationScreen
import com.github.crisacm.ui.navigation.destinations.loginDestinationScreen
import com.github.crisacm.ui.navigation.destinations.splashDestinationScreen

private const val TRANSITION_DURATION = 500

@Composable
fun AppNavigation(startDestination: Any = SplashScreen) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = startDestination,
  ) {
    composable<SplashScreen>(
      enterTransition = { fadeIn(animationSpec = tween(TRANSITION_DURATION)) },
      exitTransition = { fadeOut(animationSpec = tween(TRANSITION_DURATION)) },
      popEnterTransition = { fadeIn(animationSpec = tween(TRANSITION_DURATION)) },
      popExitTransition = { fadeOut(animationSpec = tween(TRANSITION_DURATION)) },
    ) {
      splashDestinationScreen(navController)
    }

    composable<Login>(
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
      loginDestinationScreen(navController)
    }

    composable<Home>(
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
      homeDestinationScreen(navController)
    }

    composable<EditNote>(
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
      EditNoteDestination(navController, "")
    }
  }
}

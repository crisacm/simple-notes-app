package com.github.crisacm.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.base.SIDE_EFFECTS_KEY
import com.github.crisacm.ui.navigation.Home
import com.github.crisacm.ui.navigation.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private const val SPLASH_SCREEN_DELAY = 1500L

@Composable
fun SplashScreen(
  state: SplashContracts.State,
  effect: Flow<SplashContracts.Effect>?,
  onSentEvent: (SplashContracts.Event) -> Unit,
  onNavigateTo: (Any) -> Unit,
) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
      effect
        ?.onEach { effect ->
          when (effect) {
            SplashContracts.Effect.NavigateToHome -> {
              onNavigateTo(Home)
            }

            SplashContracts.Effect.NavigateToLogin -> {
              onNavigateTo(Login)
            }

            is SplashContracts.Effect.ShowError -> {
              // Show error
            }
          }
        }?.collect()
    }

    LaunchedEffect(Unit) {
      onSentEvent(SplashContracts.Event.StartValidation)
    }

    if (state.isValidating) {
      CircularProgressIndicator(
        modifier = Modifier.size(24.dp),
      )
    }

    if (!state.isValidating && state.error != null) {
      // Show Alert Dialog error
      AlertDialog(
        onDismissRequest = { /* Handle dismiss */ },
        confirmButton = {
          TextButton(onClick = { /* Handle confirm */ }) {
            Text("OK")
          }
        },
        title = { Text("Error") },
        text = { Text(state.error) },
      )
    }
  }
}

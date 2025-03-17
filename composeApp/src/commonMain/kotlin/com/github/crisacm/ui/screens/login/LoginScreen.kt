package com.github.crisacm.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun loginScreen(
  onNavigateToHome: () -> Unit,
  viewModel: LoginViewModel = viewModel(),
) {
  val state = viewModel.viewState.value

  LaunchedEffect(true) {
    viewModel.effect.collect { effect ->
      when (effect) {
        is LoginContracts.Effect.NavigateToHome -> {
          onNavigateToHome()
        }

        is LoginContracts.Effect.ShowError -> {
          // Show error (could use a Snackbar)
        }
      }
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(
      text = "Login",
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(bottom = 32.dp),
    )

    OutlinedTextField(
      value = state.username,
      onValueChange = { viewModel.setEvent(LoginContracts.Event.OnUsernameChange(it)) },
      label = { Text("Username") },
      modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
    )

    OutlinedTextField(
      value = state.password,
      onValueChange = { viewModel.setEvent(LoginContracts.Event.OnPasswordChange(it)) },
      label = { Text("Password") },
      visualTransformation = PasswordVisualTransformation(),
      modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
    )

    if (state.errorMessage != null) {
      Text(
        text = state.errorMessage,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(vertical = 8.dp),
      )
    }

    Button(
      onClick = { viewModel.setEvent(LoginContracts.Event.OnLoginClick) },
      enabled = !state.isLoading,
      modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
    ) {
      if (state.isLoading) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
      } else {
        Text("Login")
      }
    }
  }
}

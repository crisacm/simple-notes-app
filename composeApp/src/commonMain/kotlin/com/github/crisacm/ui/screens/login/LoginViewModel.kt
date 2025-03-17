package com.github.crisacm.ui.screens.login

import com.github.crisacm.ui.base.BaseViewModel

class LoginViewModel : BaseViewModel<LoginContracts.Event, LoginContracts.State, LoginContracts.Effect>() {
  override fun setInitialState(): LoginContracts.State = LoginContracts.State()

  override fun handleEvent(event: LoginContracts.Event) {
    when (event) {
      is LoginContracts.Event.OnUsernameChange -> {
        setState { copy(username = event.username) }
      }

      is LoginContracts.Event.OnPasswordChange -> {
        setState { copy(password = event.password) }
      }

      is LoginContracts.Event.OnLoginClick -> {
        login()
      }
    }
  }

  private fun login() {
    val currentState = viewState.value
    setState { copy(isLoading = true, errorMessage = null) }

    // Mock login - replace with actual authentication
    if (currentState.username == "admin" && currentState.password == "password") {
      // Save session information (use a SessionManager in a real app)
      setEffect { LoginContracts.Effect.NavigateToHome }
    } else {
      setState {
        copy(
          isLoading = false,
          errorMessage = "Invalid username or password",
        )
      }
      setEffect { LoginContracts.Effect.ShowError("Login failed") }
    }
  }
}

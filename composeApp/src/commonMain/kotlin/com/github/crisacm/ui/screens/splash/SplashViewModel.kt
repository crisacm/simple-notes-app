package com.github.crisacm.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.github.crisacm.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashContracts.Event, SplashContracts.State, SplashContracts.Effect>() {
  override fun setInitialState(): SplashContracts.State =
    SplashContracts.State(
      isValidating = true,
    )

  override fun handleEvent(event: SplashContracts.Event) {
    when (event) {
      SplashContracts.Event.StartValidation -> validateSession()
    }
  }

  private fun validateSession() {
    setState { copy(isValidating = true) }

    viewModelScope.launch {
      delay(1000) // Simulate network/database check

      try {
        // Here you would call your repository/service to check auth status
        val isLoggedIn = checkUserLoggedIn()

        if (isLoggedIn) {
          setEffect { SplashContracts.Effect.NavigateToHome }
        } else {
          setEffect { SplashContracts.Effect.NavigateToLogin }
        }
      } catch (e: Exception) {
        setEffect { SplashContracts.Effect.ShowError("Authentication error: ${e.message}") }
      } finally {
        setState { copy(isValidating = false) }
      }
    }
  }

  private suspend fun checkUserLoggedIn(): Boolean {
    // Implementation would depend on your authentication system
    // This could check a token in DataStore/SharedPreferences
    // or validate against a local database or remote API
    return false // Default to not logged in
  }
}

package com.github.crisacm.ui.screens.splash

import com.github.crisacm.ui.base.ViewEvent
import com.github.crisacm.ui.base.ViewSideEffect
import com.github.crisacm.ui.base.ViewState

class SplashContracts {
  sealed class Event : ViewEvent {
    data object StartValidation : Event()
  }

  data class State(
    val isValidating: Boolean = false,
    val error: String? = null,
  ) : ViewState

  sealed class Effect : ViewSideEffect {
    data object NavigateToLogin : Effect()

    data object NavigateToHome : Effect()

    data class ShowError(
      val message: String,
    ) : Effect()
  }
}

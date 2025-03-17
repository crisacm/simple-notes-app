package com.github.crisacm.ui.screens.login

import com.github.crisacm.ui.base.ViewEvent
import com.github.crisacm.ui.base.ViewSideEffect
import com.github.crisacm.ui.base.ViewState

class LoginContracts {
  sealed class Event : ViewEvent {
    data class OnUsernameChange(
      val username: String,
    ) : Event()

    data class OnPasswordChange(
      val password: String,
    ) : Event()

    data object OnLoginClick : Event()
  }

  data class State(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
  ) : ViewState

  sealed class Effect : ViewSideEffect {
    data object NavigateToHome : Effect()

    data class ShowError(
      val message: String,
    ) : Effect()
  }
}

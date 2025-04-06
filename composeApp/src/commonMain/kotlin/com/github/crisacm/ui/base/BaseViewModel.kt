package com.github.crisacm.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Marker interface for view events.
 */
interface ViewEvent

/**
 * Marker interface for view states.
 */
interface ViewState

/**
 * Marker interface for view side effects.
 */
interface ViewSideEffect

/**
 * Constant key for side effects.
 */
const val SIDE_EFFECTS_KEY = "side-effects_key"

/**
 * Base ViewModel class to manage UI state, events, and side effects.
 *
 * @param Event The type of events that the ViewModel will handle.
 * @param UiState The type of UI state that the ViewModel will manage.
 * @param Effect The type of side effects that the ViewModel will produce.
 */
abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> : ViewModel() {
  /**
   * Sets the initial state of the UI.
   *
   * @return The initial UI state.
   */
  abstract fun setInitialState(): UiState

  /**
   * Handles the given event.
   *
   * @param event The event to handle.
   */
  abstract fun handleEvent(event: Event)

  // Lazy initialization of the initial state.
  private val initialState: UiState by lazy { setInitialState() }

  // Mutable state to hold the current UI state.
  private val _viewState: MutableState<UiState> = mutableStateOf(initialState)

  // Publicly exposed immutable state.
  val viewState: State<UiState> = _viewState

  // Shared flow to handle events.
  private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

  // Channel to handle side effects.
  private val _effect: Channel<Effect> = Channel()

  // Publicly exposed flow of side effects.
  val effect = _effect.receiveAsFlow()

  init {
    // Subscribe to events when the ViewModel is initialized.
    subscribeToEvents()
  }

  /**
   * Subscribes to events and handles them.
   */
  private fun subscribeToEvents() {
    viewModelScope.launch {
      _event.collect {
        handleEvent(it)
      }
    }
  }

  /**
   * Sets a new event.
   *
   * @param event The event to set.
   */
  fun setEvent(event: Event) {
    viewModelScope.launch { _event.emit(event) }
  }

  /**
   * Updates the UI state using the given reducer function.
   *
   * @param reducer The function to update the state.
   */
  protected fun setState(reducer: UiState.() -> UiState) {
    val newState = viewState.value.reducer()
    _viewState.value = newState
  }

  /**
   * Sets a new side effect.
   *
   * @param builder The function to create the side effect.
   */
  protected fun setEffect(builder: () -> Effect) {
    val effectValue = builder()
    viewModelScope.launch { _effect.send(effectValue) }
  }
}

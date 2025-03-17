package com.github.crisacm.ui.screens.home

import com.github.crisacm.domain.model.fakeNotesList
import com.github.crisacm.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeContracts.Event, HomeContracts.State, HomeContracts.Effect>() {
  override fun setInitialState(): HomeContracts.State = HomeContracts.State(notes = fakeNotesList)

  override fun handleEvent(event: HomeContracts.Event) {
    //
  }
}

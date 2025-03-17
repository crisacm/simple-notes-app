package com.github.crisacm

import androidx.compose.ui.window.ComposeUIViewController
import com.github.crisacm.di.initKoin

fun MainViewController() =
  ComposeUIViewController(
    configure = {
      initKoin()
    },
  ) { App() }

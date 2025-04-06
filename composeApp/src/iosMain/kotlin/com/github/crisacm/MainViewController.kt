package com.github.crisacm

import androidx.compose.ui.window.ComposeUIViewController
import com.github.crisacm.di.initKoin
import com.github.crisacm.utils.WebBrowser

fun MainViewController() =
  ComposeUIViewController(
    configure = {
      initKoin()
    },
  ) { App(WebBrowser()) }

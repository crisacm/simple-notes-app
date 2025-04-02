package com.github.crisacm

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.github.crisacm.ui.navigation.AppNavigation
import com.github.crisacm.ui.navigation.EditNote
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.utils.WebBrowser
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(webBrowser: WebBrowser) {
  MaterialTheme(
    colorScheme =
      darkColorScheme(
        background = GrayDarkBackground,
      ),
  ) {
    // AppNavigation(startDestination = EditNote(1), webBrowser = webBrowser)
    AppNavigation(webBrowser = webBrowser)
  }
}

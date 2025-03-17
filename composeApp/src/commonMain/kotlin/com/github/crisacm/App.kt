package com.github.crisacm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.crisacm.ui.navigation.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
  MaterialTheme {
    AppNavigation()
  }
}

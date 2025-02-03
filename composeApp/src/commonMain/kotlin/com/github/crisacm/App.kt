package com.github.crisacm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.github.crisacm.ui.navigation.AppNavigation
import com.github.crisacm.ui.navigation.EditNote
import com.github.crisacm.ui.navigation.Home
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
  MaterialTheme {
    AppNavigation(Home)
    // AppNavigation(EditNote(""))
  }
}

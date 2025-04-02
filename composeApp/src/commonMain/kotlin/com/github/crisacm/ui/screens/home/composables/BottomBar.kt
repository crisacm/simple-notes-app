package com.github.crisacm.ui.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground

@Suppress("MagicNumber")
@Composable
fun BottomBar(
  modifier: Modifier = Modifier,
  barHeight: Dp = 60.dp,
  barColor: Color = GrayDarkBottomBarBackground,
  cardTopCornerSize: Dp = 24.dp,
  cardElevation: Dp = 8.dp,
  buttons: List<BottomBarItemData> = emptyList(),
) {
  require(buttons.size == 4) { "BottomBar must have exactly 4 buttons" }
  Box(
    modifier = modifier.fillMaxWidth().height(barHeight),
  ) {
    Card(
      modifier =
        Modifier
          .fillMaxWidth()
          .height(barHeight)
          .align(Alignment.BottomCenter),
      colors = CardDefaults.cardColors(containerColor = barColor),
      elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
      shape =
        RoundedCornerShape(
          topStart = cardTopCornerSize,
          topEnd = cardTopCornerSize,
          bottomEnd = 0.dp,
          bottomStart = 0.dp,
        ),
    ) {
      Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
      ) {
        BottomBarItem(buttons[0])
        BottomBarItem(buttons[1])
        Spacer(modifier = Modifier.size(64.dp))
        BottomBarItem(buttons[2])
        BottomBarItem(buttons[3])
      }
    }
  }
}

package com.github.crisacm.ui.screens.home.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.BottomBarItemData
import com.github.crisacm.ui.theme.GrayLightIcons
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBarItem(
  itemData: BottomBarItemData,
  selectedColor: Color = GrayLightIcons,
  unselectedColor: Color = GrayLightIcons,
  iconSize: Dp = 24.dp,
) {
  IconButton(onClick = itemData.onClick) {
    Icon(
      painter = painterResource(itemData.drawableId),
      contentDescription = null,
      tint = if (itemData.selected) selectedColor else unselectedColor,
      modifier = Modifier.size(iconSize),
    )
  }
}

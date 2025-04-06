package com.github.crisacm.ui.composable

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Suppress("MagicNumber")
@Composable
fun RoundedCheckbox(
  modifier: Modifier = Modifier,
  isChecked: Boolean,
  size: Float = 24f,
  checkedColor: Color = Color.Blue.copy(alpha = 0.7f),
  iconCheckedColor: Color = Color.White,
  uncheckedColor: Color = Color.White,
  borderColor: Color = Color.Gray,
  onValueChange: (Boolean) -> Unit,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier =
      modifier
        .toggleable(
          value = isChecked,
          role = Role.Checkbox,
          onValueChange = onValueChange,
        ),
  ) {
    Card(
      modifier = Modifier.background(Color.Transparent),
      elevation = CardDefaults.elevatedCardElevation(0.dp),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = if (isChecked) checkedColor else uncheckedColor),
      border = BorderStroke(2.dp, color = borderColor),
    ) {
      val density = LocalDensity.current
      val duration = 200

      Box(
        modifier =
          Modifier
            .size(25.dp)
            .clickable { onValueChange(!isChecked) },
        contentAlignment = Alignment.Center,
      ) {
        androidx.compose.animation.AnimatedVisibility(
          visible = isChecked,
          enter =
            slideInHorizontally(animationSpec = tween(duration)) {
              with(density) { (size * -0.5).dp.roundToPx() }
            } +
              expandHorizontally(
                expandFrom = Alignment.Start,
                animationSpec = tween(duration),
              ),
          exit = fadeOut(),
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = iconCheckedColor,
            modifier = Modifier.padding(4.dp),
          )
        }
      }
    }
  }
}

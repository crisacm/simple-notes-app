package com.github.crisacm.ui.screens.home.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.TagSelected

@Composable
fun CardTag(
  modifier: Modifier = Modifier,
  tag: Tag,
  isSelected: Boolean,
  onClick: () -> Unit,
) {
  Crossfade(targetState = isSelected) { selected ->
    when (selected) {
      true -> {
        Card(
          modifier = modifier,
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors().copy(containerColor = TagSelected),
          onClick = onClick,
        ) {
          Text(
            modifier = Modifier.padding(18.dp, 10.dp, 18.dp, 10.dp),
            text = tag.name,
            color = GrayDarkBackground.copy(alpha = 0.87f),
          )
        }
      }

      false -> {
        Card(
          modifier = modifier,
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors().copy(containerColor = GrayDarkBackground),
          border = BorderStroke(1.dp, GrayDarkBottomBarBackground),
          onClick = onClick,
        ) {
          Text(
            modifier = Modifier.padding(18.dp, 10.dp, 18.dp, 10.dp),
            text = tag.name,
            color = GrayLightIcons.copy(alpha = 0.87f),
          )
        }
      }
    }
  }
}

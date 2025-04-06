package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_shoppingmode
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectTag(
  modifier: Modifier = Modifier,
  tag: Tag?,
  onClick: () -> Unit,
) {
  Card(
    modifier = modifier.padding(horizontal = 24.dp),
    onClick = onClick,
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
  ) {
    Row(
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        modifier = Modifier.size(18.dp),
        painter = painterResource(Res.drawable.ic_shoppingmode),
        contentDescription = "Tag Icon",
        tint = GrayLightIcons,
      )
      Text(
        modifier = Modifier.padding(start = 6.dp),
        text = tag?.name ?: "[No tag]",
        color = GrayLightIcons,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Icon(
        modifier =
          Modifier
            .size(18.dp)
            .padding(start = 6.dp),
        imageVector = Icons.Default.ArrowDropDown,
        contentDescription = "Select tag",
        tint = GrayLightIcons,
      )
    }
  }
}

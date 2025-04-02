package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomEditorOptionsBar(
  modifier: Modifier = Modifier,
  options: List<EditorOption>,
  onOptionClick: (EditorOption) -> Unit,
) {
  Row(
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = "Add Content",
      color = GrayLightIcons,
      fontWeight = FontWeight.SemiBold,
      modifier = Modifier.padding(end = 12.dp),
    )
    LazyRow(
      modifier =
        modifier
          .padding(start = 12.dp)
          .fillMaxWidth(),
    ) {
      options.forEachIndexed { index, _ ->
        item {
          Card(
            modifier = Modifier,
            shape =
              RoundedCornerShape(
                topStart = if (index == 0) 25.dp else 0.dp,
                bottomStart = if (index == 0) 25.dp else 0.dp,
                topEnd = if (index == options.size - 1) 25.dp else 0.dp,
                bottomEnd = if (index == options.size - 1) 25.dp else 0.dp,
              ),
            colors = CardDefaults.cardColors().copy(containerColor = GrayDarkBottomBarBackground),
          ) {
            IconButton(
              modifier =
                Modifier
                  .padding(
                    start = if (index == 0) 6.dp else 0.dp,
                    end = 6.dp,
                  ),
              onClick = { onOptionClick(options[index]) },
            ) {
              Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(options[index].icon),
                contentDescription = options[index].desc,
                tint = GrayLightIcons,
              )
            }
          }
        }
      }
    }
  }
}

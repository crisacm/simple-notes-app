package com.github.crisacm.ui.screens.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.screens.home.ListStyle
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_space_dashboard
import just_notes_kmp.composeapp.generated.resources.ic_view_day
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod")
@Composable
fun TopBarWithSearch(
  modifier: Modifier = Modifier,
  value: String = "",
  onValueChange: (String) -> Unit = {},
  onSearch: (String) -> Unit = {},
  listStyle: ListStyle = ListStyle.GRID,
  onListStyleChange: (ListStyle) -> Unit = {},
  onProfileClick: () -> Unit = {},
) {
  val searchTextFieldFocus = FocusRequester()
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(56.dp),
    shape = RoundedCornerShape(24.dp),
    colors = CardDefaults.cardColors().copy(containerColor = GrayDarkBottomBarBackground),
  ) {
    Row(
      modifier =
        Modifier
          .padding(start = 8.dp, end = 8.dp)
          .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      TextField(
        modifier =
          Modifier
            .weight(1f)
            .focusRequester(searchTextFieldFocus),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Search notes") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions =
          KeyboardActions(
            onSearch = {
              onSearch(value)
              searchTextFieldFocus.freeFocus()
            },
          ),
        trailingIcon = {
          if (value.isNotEmpty()) {
            Icon(
              imageVector = Icons.Default.Clear,
              contentDescription = null,
              tint = GrayLightIcons,
              modifier =
                Modifier.clickable(
                  onClick = {
                    onValueChange("")
                    searchTextFieldFocus.freeFocus()
                  },
                ),
            )
          }
        },
        colors =
          TextFieldDefaults.colors(
            focusedTextColor = GrayLightIcons,
            unfocusedTextColor = GrayLightIcons,
            focusedContainerColor = GrayDarkBottomBarBackground,
            unfocusedContainerColor = GrayDarkBottomBarBackground,
            focusedPlaceholderColor = GrayLightIcons,
            unfocusedPlaceholderColor = GrayLightIcons,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
          ),
      )
      Icon(
        modifier =
          Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(24.dp)
            .clickable(
              onClick = {
                if (listStyle == ListStyle.LIST) {
                  onListStyleChange(ListStyle.GRID)
                } else {
                  onListStyleChange(
                    ListStyle.LIST,
                  )
                }
              },
            ),
        painter =
          painterResource(
            when (listStyle) {
              ListStyle.LIST -> Res.drawable.ic_space_dashboard
              ListStyle.GRID -> Res.drawable.ic_view_day
            },
          ),
        contentDescription = null,
        tint = GrayLightIcons,
      )
      Card(
        modifier = Modifier.size(36.dp),
        shape = CircleShape,
        onClick = onProfileClick,
      ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Image(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Person,
            contentDescription = null,
          )
        }
      }
    }
  }
}

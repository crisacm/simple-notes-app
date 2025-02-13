@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.BottomBarItemData
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.model.fakeNotesList
import com.github.crisacm.domain.model.fakeTagsList
import com.github.crisacm.ui.screens.home.composables.CardNote
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkBottomBarBackground
import com.github.crisacm.ui.theme.GrayDarkFabBackground
import com.github.crisacm.ui.theme.GrayDarkFabBorder
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.TagSelected
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check
import just_notes_kmp.composeapp.generated.resources.ic_edit_text
import just_notes_kmp.composeapp.generated.resources.ic_mic
import just_notes_kmp.composeapp.generated.resources.ic_picture
import just_notes_kmp.composeapp.generated.resources.ic_space_dashboard
import just_notes_kmp.composeapp.generated.resources.ic_view_day
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("LongMethod")
@Composable
fun HomeScreen(navigateToEditNote: (String) -> Unit) {
  val tags = fakeTagsList
  val notes = fakeNotesList

  val searchText = remember { mutableStateOf("") }
  val listStyle = remember { mutableStateOf(ListStyle.GRID) }
  val selectedTags = remember { mutableStateOf(0) }
  val openProfileDialog = remember { mutableStateOf(false) }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopBarWithSearch(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        onSearch = {},
        listStyle = listStyle.value,
        onListStyleChange = { listStyle.value = it },
        onProfileClick = { openProfileDialog.value = true },
      )
    },
    bottomBar = {
      val items =
        listOf(
          Res.drawable.ic_check,
          Res.drawable.ic_edit_text,
          Res.drawable.ic_mic,
          Res.drawable.ic_picture,
        )

      val buttons =
        items.mapIndexed { index, drawableId ->
          BottomBarItemData(
            drawableId = drawableId,
            onClick = {
              when (index) {
                0 -> { // Check
                }

                1 -> { // Text
                }

                2 -> { // Mic
                }

                3 -> { // Picture
                }
              }
            },
          )
        }

      BottomBar(
        modifier = Modifier,
        cardTopCornerSize = 0.dp,
        buttons = buttons,
      )
    },
    floatingActionButton = {
      LargeFloatingActionButton(
        modifier = Modifier.size(64.dp).offset(y = (48).dp),
        onClick = { navigateToEditNote("") },
        shape = CircleShape,
        containerColor = GrayDarkFabBackground,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 6.dp),
      ) {
        Card(
          modifier = Modifier.size(64.dp),
          shape = CircleShape,
          colors = CardDefaults.cardColors(containerColor = GrayDarkFabBackground),
          border = BorderStroke(2.dp, GrayDarkFabBorder),
        ) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = Icons.TwoTone.Add,
              contentDescription = null,
              tint = GrayLightIcons,
              modifier = Modifier.size(32.dp),
            )
          }
        }
      }
    },
    floatingActionButtonPosition = FabPosition.Center,
  ) { paddingValues ->
    Box(
      modifier =
        Modifier
          .fillMaxSize()
          .background(GrayDarkBackground)
          .padding(paddingValues),
    ) {
      Column {
        LazyRow {
          tags.forEachIndexed { index, item ->
            val startPaddingValues = if (index == 0) 16.dp else 6.dp
            val endPaddingValues = if (index == tags.size - 1) 16.dp else 6.dp

            item {
              CardTag(
                modifier = Modifier.padding(start = startPaddingValues, bottom = 6.dp, end = endPaddingValues),
                item,
                isSelected = index == selectedTags.value,
                onClick = { selectedTags.value = index },
              )
            }
          }
        }
        LazyVerticalStaggeredGrid(
          modifier = Modifier.padding(start = 12.dp, top = 16.dp, end = 12.dp),
          columns = StaggeredGridCells.Fixed(if (listStyle.value == ListStyle.GRID) 2 else 1),
          verticalItemSpacing = 10.dp,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          items(notes.size) { index ->
            CardNote(
              modifier = Modifier.padding(bottom = if (index == notes.size - 1) 16.dp else 0.dp),
              note = notes[index],
              onClick = {},
            )
          }
        }
      }
    }

    if (openProfileDialog.value) {
      AlertDialog(
        onDismissRequest = { },
        title = { Text("Profile") },
        text = { Text("Profile content") },
        confirmButton = {
          TextButton(
            onClick = {
              openProfileDialog.value = false
            },
          ) {
            Text("Confirm")
          }
        },
        dismissButton = {
          TextButton(
            onClick = {
              openProfileDialog.value = false
            },
          ) {
            Text("Dismiss")
          }
        },
      )
    }
  }
}

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

enum class ListStyle {
  LIST,
  GRID,
}

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

@Suppress("MagicNumber")
@Composable
fun BottomBar(
  modifier: Modifier = Modifier,
  barHeight: Dp = 60.dp,
  barColor: Color = GrayDarkBottomBarBackground,
  cardTopCornerSize: Dp = 24.dp,
  cardElevation: Dp = 8.dp,
  buttons: List<BottomBarItemData> = emptyList<BottomBarItemData>(),
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

@Preview
@Composable
fun HomeScreenPreview() {
  HomeScreen {}
}

@Preview
@Composable
fun TopBarWithSearchPreview() {
  TopBarWithSearch {}
}

@Preview
@Composable
fun CardTagPreview() {
  CardTag(tag = Tag("1", "Tag"), isSelected = false, onClick = {})
}

@Preview
@Composable
fun BottomBarPreview() {
  BottomBar(
    modifier = Modifier,
    buttons =
      listOf(
        BottomBarItemData(Res.drawable.ic_check),
        BottomBarItemData(Res.drawable.ic_edit_text),
        BottomBarItemData(Res.drawable.ic_mic),
        BottomBarItemData(Res.drawable.ic_picture),
      ),
  )
}

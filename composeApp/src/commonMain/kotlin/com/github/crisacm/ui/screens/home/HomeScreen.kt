@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.BottomBarItemData
import com.github.crisacm.domain.model.fakeNotesList
import com.github.crisacm.domain.model.fakeTagsList
import com.github.crisacm.ui.screens.home.composables.BottomBar
import com.github.crisacm.ui.screens.home.composables.CardNote
import com.github.crisacm.ui.screens.home.composables.CardTag
import com.github.crisacm.ui.screens.home.composables.ListStyle
import com.github.crisacm.ui.screens.home.composables.TopBarWithSearch
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkFabBackground
import com.github.crisacm.ui.theme.GrayDarkFabBorder
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check
import just_notes_kmp.composeapp.generated.resources.ic_edit_text
import just_notes_kmp.composeapp.generated.resources.ic_mic
import just_notes_kmp.composeapp.generated.resources.ic_picture

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

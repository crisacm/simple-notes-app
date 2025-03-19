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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.BottomBarItemData
import com.github.crisacm.domain.model.fakeTagsList
import com.github.crisacm.ui.base.SIDE_EFFECTS_KEY
import com.github.crisacm.ui.screens.home.composables.BottomBar
import com.github.crisacm.ui.screens.home.composables.CardNote
import com.github.crisacm.ui.screens.home.composables.TagItem
import com.github.crisacm.ui.screens.home.composables.TopBarWithSearch
import com.github.crisacm.ui.screens.home.composables.UserAccountDialog
import com.github.crisacm.ui.theme.GrayDarkBackground
import com.github.crisacm.ui.theme.GrayDarkFabBackground
import com.github.crisacm.ui.theme.GrayDarkFabBorder
import com.github.crisacm.ui.theme.GrayLightIcons
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check
import just_notes_kmp.composeapp.generated.resources.ic_edit_text
import just_notes_kmp.composeapp.generated.resources.ic_mic
import just_notes_kmp.composeapp.generated.resources.ic_picture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Suppress("LongMethod")
@Composable
fun HomeScreen(
  state: HomeContracts.State,
  effect: Flow<HomeContracts.Effect>?,
  onEventSent: (HomeContracts.Event) -> Unit,
  navigateTo: (HomeContracts.Effect.Navigation) -> Unit,
) {
  val tags = fakeTagsList

  val searchText = remember { mutableStateOf("") }
  val listStyle = remember { mutableStateOf(ListStyle.GRID) }
  val selectedTags = remember { mutableStateOf(0) }
  var openProfileDialog by remember { mutableStateOf(false) }

  LaunchedEffect(SIDE_EFFECTS_KEY) {
    effect
      ?.onEach { effect ->
        when (effect) {
          is HomeContracts.Effect.Navigation.ToEdit -> {
            navigateTo(effect)
          }

          is HomeContracts.Effect.Navigation.ToGitHub -> {
            navigateTo(effect)
          }
        }
      }?.collect()
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopBarWithSearch(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        onSearch = {},
        listStyle = listStyle.value,
        onListStyleChange = { listStyle.value = it },
        onProfileClick = { openProfileDialog = true },
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
        onClick = { onEventSent(HomeContracts.Event.Edit("")) },
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
              TagItem(
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
          items(state.notes.size) { index ->
            CardNote(
              modifier = Modifier.padding(bottom = if (index == state.notes.size - 1) 16.dp else 0.dp),
              note = state.notes[index],
              onClick = { onEventSent(HomeContracts.Event.Edit(state.notes[index].id)) },
            )
          }
        }
      }
    }

    if (openProfileDialog) {
      UserAccountDialog({
        onEventSent(HomeContracts.Event.GoToGithub)
      }, { openProfileDialog = false })
    }
  }
}

enum class ListStyle {
  LIST,
  GRID,
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.ui.theme.GrayLightIcons
import kotlinx.coroutines.launch

@Composable
fun SelectTagModalSheet(
  visibility: Boolean,
  onVisibilityChange: (Boolean) -> Unit = {},
  tags: List<Tag>,
  selectedTag: Tag?,
  onSelectTag: (Tag) -> Unit,
  onCreateTag: (String) -> Unit,
  onRemoveTag: () -> Unit,
  onDeleteTag: (Tag) -> Unit,
) {
  val scope = rememberCoroutineScope()
  val bottomSheetStateTags = rememberModalBottomSheetState()

  if (visibility) {
    ModalBottomSheet(
      onDismissRequest = { onVisibilityChange(false) },
      sheetState = bottomSheetStateTags,
      shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
      SelectTagSheetContent(
        modifier = Modifier,
        tags = tags,
        actualTag = selectedTag,
        onSelectTag = {
          scope
            .launch { bottomSheetStateTags.hide() }
            .invokeOnCompletion { onVisibilityChange(false) }
          onSelectTag(it)
        },
        onCreateTag = {
          scope
            .launch { bottomSheetStateTags.hide() }
            .invokeOnCompletion { onVisibilityChange(false) }
          onCreateTag(it)
        },
        onRemoveTag = {
          scope
            .launch { bottomSheetStateTags.hide() }
            .invokeOnCompletion { onVisibilityChange(false) }
          onRemoveTag()
        },
        onDeleteTag = {
          onDeleteTag(it)
        },
      )
    }
  }
}

@Suppress("LongMethod")
@Composable
fun SelectTagSheetContent(
  modifier: Modifier = Modifier,
  tags: List<Tag>,
  actualTag: Tag?,
  onSelectTag: (Tag) -> Unit,
  onCreateTag: (String) -> Unit,
  onRemoveTag: () -> Unit,
  onDeleteTag: (Tag) -> Unit,
) {
  var filterTagsBy by remember { mutableStateOf("") }
  var selectedTag by remember { mutableStateOf(actualTag) }
  val filteredTags =
    tags
      .filter {
        if (filterTagsBy.isNotEmpty()) {
          (it.name.contains(filterTagsBy, ignoreCase = true))
        } else {
          true
        }
      }

  Column(
    modifier
      .padding(start = 24.dp, end = 24.dp, bottom = 12.dp)
      .fillMaxWidth(),
  ) {
    Card(
      modifier =
        Modifier
          .padding(bottom = 12.dp)
          .fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
    ) {
      TextField(
        value = filterTagsBy,
        onValueChange = {
          filterTagsBy = it
          if (selectedTag != null) {
            selectedTag = null
          }
        },
        placeholder = { Text("Search Tag By", color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
          Icon(
            Icons.Default.Search,
            contentDescription = "Search icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp),
          )
        },
        trailingIcon = {
          if (filterTagsBy.isNotEmpty()) {
            IconButton(onClick = { filterTagsBy = "" }) {
              Icon(
                Icons.Default.Clear,
                contentDescription = "Clear Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp),
              )
            }
          }
        },
        colors =
          TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.LightGray.copy(alpha = 0.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
          ),
      )
    }
    LazyColumn(Modifier.padding(vertical = 12.dp)) {
      if (filteredTags.isEmpty()) {
        item {
          Text(
            text = "Not are Tags to select",
            modifier =
              Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.LightGray,
            textAlign = TextAlign.Center,
          )
        }
      } else {
        items(filteredTags, key = { item -> item.id!! }) { tag ->
          Card(
            modifier =
              Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            onClick = { selectedTag = if (selectedTag == tag) null else tag },
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Checkbox(
                checked = (selectedTag == tag),
                onCheckedChange = { selectedTag = if (it) tag else null },
              )
              Text(
                text = tag.name,
                color = GrayLightIcons,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                  Modifier
                    .weight(1f),
              )
              IconButton(onClick = { onDeleteTag(tag) }) {
                Icon(
                  Icons.Default.Delete,
                  contentDescription = "Delete tag icon",
                  tint = GrayLightIcons,
                  modifier = Modifier.size(24.dp),
                )
              }
            }
          }
        }
      }
    }
    Button(
      onClick = {
        if (selectedTag == null) {
          onCreateTag(filterTagsBy)
        } else {
          onSelectTag(selectedTag!!)
        }
      },
      shape = RoundedCornerShape(16.dp),
      enabled = (selectedTag != null) || (filterTagsBy.isNotEmpty()),
      modifier = Modifier.fillMaxWidth(),
    ) {
      val text =
        if (filterTagsBy.isNotEmpty() && filteredTags.isEmpty()) {
          "Create and Set Tag: $filterTagsBy"
        } else {
          "Set Tag"
        }

      Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
    if (actualTag != null) {
      Button(
        onClick = { onRemoveTag() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        modifier =
          Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors =
          ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
          ),
      ) {
        Text(
          text = "Remove actual tag",
          color = Color.LightGray,
        )
      }
    }
  }
}

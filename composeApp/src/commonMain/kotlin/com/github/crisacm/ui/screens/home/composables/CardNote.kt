@file:OptIn(ExperimentalFoundationApi::class)

package com.github.crisacm.ui.screens.home.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.utils.NoteColors
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.NoteBlue
import com.github.crisacm.ui.theme.NoteGreen
import com.github.crisacm.ui.theme.NoteOrange
import com.github.crisacm.ui.theme.NotePurple
import com.github.crisacm.ui.theme.NoteWhite
import com.github.crisacm.ui.theme.NoteYellow
import com.github.crisacm.ui.theme.NoteYellowDark
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_add_photo
import just_notes_kmp.composeapp.generated.resources.ic_check_box
import just_notes_kmp.composeapp.generated.resources.ic_check_box_empty
import org.jetbrains.compose.resources.painterResource

@Suppress("MagicNumber", "LongMethod")
@Composable
fun CardNote(
  modifier: Modifier = Modifier,
  note: Note,
  contents: List<NoteContent>,
  onClick: () -> Unit,
  onLongClick: (() -> Unit)? = null,
) {
  val noteColor =
    when (note.color) {
      NoteColors.White -> NoteWhite
      NoteColors.Yellow -> NoteYellow
      NoteColors.YellowDark -> NoteYellowDark
      NoteColors.Orange -> NoteOrange
      NoteColors.Purple -> NotePurple
      NoteColors.Green -> NoteGreen
      NoteColors.Blue -> NoteBlue
    }

  Card(
    modifier =
      modifier
        .combinedClickable(
          onClick = onClick,
          onLongClick = onLongClick,
        ),
    shape = RoundedCornerShape(26.dp),
    colors = CardDefaults.cardColors().copy(containerColor = noteColor),
  ) {
    Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)) {
      val noteContainsImage = contents.any { it.type == NoteContentType.IMAGE }

      if (noteContainsImage) {
        val noteFirstImage: String = contents.filter { it.type == NoteContentType.IMAGE }[0].content

        Card(
          modifier = Modifier.fillMaxWidth().padding(top = 4.dp).height(120.dp),
          colors = CardDefaults.cardColors().copy(containerColor = NoteYellowDark),
        ) {
          if (noteFirstImage.isNotEmpty()) {
            setSingletonImageLoaderFactory { context ->
              ImageLoader
                .Builder(context)
                .crossfade(true)
                .logger(DebugLogger())
                .build()
            }

            AsyncImage(
              model = noteFirstImage,
              contentDescription = "Note image",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxWidth().aspectRatio(2 / 3f).clip(MaterialTheme.shapes.medium),
            )
          } else {
            Box(
              modifier = Modifier.fillMaxWidth().height(120.dp),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                painterResource(Res.drawable.ic_add_photo),
                contentDescription = "add image icon",
                modifier =
                  Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 8.dp)
                    .size(24.dp),
                tint = GrayLightIcons,
              )
            }
          }
        }
      }

      Text(
        modifier = Modifier.padding(top = 8.dp),
        text = note.title.ifEmpty { "Note title" },
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        color = GrayLightIcons,
      )

      val noteOnlyContainsChecklist = contents.all { it.type == NoteContentType.CHECKLIST }
      if (!noteContainsImage && noteOnlyContainsChecklist) {
        contents
          .filter { it.type == NoteContentType.CHECKLIST }
          .take(3)
          .forEachIndexed { index, content ->
            val topPadding = if (index == 0) 12.dp else 4.dp

            Card(
              modifier = Modifier.fillMaxWidth().padding(top = topPadding, bottom = 4.dp),
              shape = RoundedCornerShape(26.dp),
              colors =
                CardDefaults.cardColors().copy(
                  containerColor =
                    noteColor.copy(
                      red = noteColor.red * 0.9f,
                      green = noteColor.green * 0.9f,
                      blue = noteColor.blue * 0.9f,
                    ),
                ),
            ) {
              Row(
                modifier = Modifier.padding(start = 18.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
              ) {
                val icon = if (content.checked) Res.drawable.ic_check_box else Res.drawable.ic_check_box_empty
                Icon(
                  painter = painterResource(icon),
                  contentDescription = null,
                  modifier = Modifier.size(16.dp),
                  tint = GrayLightIcons,
                )
                Text(
                  modifier = Modifier.padding(start = 8.dp),
                  text = content.content.ifEmpty { "Check value" },
                  color = GrayLightIcons,
                  fontSize = 12.sp,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                )
              }
            }
          }
      }

      val noteContainText = contents.any { it.type == NoteContentType.TEXT }
      if (noteContainText) {
        val noteFirstText: String = contents.filter { it.type == NoteContentType.TEXT }[0].content

        Text(
          modifier = Modifier.padding(top = 4.dp),
          text = noteFirstText.ifEmpty { "Note content" },
          fontSize = 12.sp,
          overflow = TextOverflow.Ellipsis,
          lineHeight = 20.sp,
          maxLines = 6,
          color = GrayLightIcons,
        )
      }
    }
  }
}

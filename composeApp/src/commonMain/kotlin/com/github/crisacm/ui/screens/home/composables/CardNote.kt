package com.github.crisacm.ui.screens.home.composables

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
import com.github.crisacm.domain.model.NoteContentType
import com.github.crisacm.domain.model.fakeNotesList
import com.github.crisacm.ui.theme.GrayLightIcons
import com.github.crisacm.ui.theme.NoteYellowDark
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check_box
import just_notes_kmp.composeapp.generated.resources.ic_check_box_empty
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("MagicNumber", "LongMethod")
@Composable
fun CardNote(
  modifier: Modifier = Modifier,
  note: Note,
  onClick: () -> Unit,
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(26.dp),
    colors = CardDefaults.cardColors().copy(containerColor = note.color),
    onClick = onClick,
  ) {
    Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)) {
      val noteContainsImage = note.content.filter { it.type == NoteContentType.IMAGE }.isNotEmpty()

      if (noteContainsImage) {
        val noteFirstImage: String = note.content.filter { it.type == NoteContentType.IMAGE }[0].content as String

        Card(
          modifier = Modifier.fillMaxWidth().padding(top = 4.dp).height(120.dp),
          colors = CardDefaults.cardColors().copy(containerColor = NoteYellowDark),
        ) {
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
        }
      }

      Text(
        modifier = Modifier.padding(top = 8.dp),
        text = note.title,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        color = GrayLightIcons,
      )

      val noteOnlyContainsChecklist = note.content.all { it.type == NoteContentType.CHECKLIST }
      if (!noteContainsImage && noteOnlyContainsChecklist) {
        note.content
          .filter { it.type == NoteContentType.CHECKLIST }
          .take(3)
          .forEachIndexed { index, content ->
            val topPadding = if (index == 0) 12.dp else 4.dp

            Card(
              modifier = Modifier.fillMaxWidth().padding(top = topPadding, bottom = 4.dp),
              shape = RoundedCornerShape(26.dp),
              colors = CardDefaults.cardColors().copy(containerColor = NoteYellowDark),
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
                )
                Text(
                  modifier = Modifier.padding(start = 8.dp),
                  text = content.content as String,
                  fontSize = 12.sp,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                )
              }
            }
          }
      }

      val noteContainText = note.content.any { it.type == NoteContentType.TEXT }
      if (noteContainText) {
        val noteFirstText: String = note.content.filter { it.type == NoteContentType.TEXT }[0].content as String

        Text(
          modifier = Modifier.padding(top = 4.dp),
          text = noteFirstText,
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

@Preview
@Composable
fun CardNoteWhitePreview() {
  CardNote(
    note = fakeNotesList[0],
    onClick = {},
  )
}

@Preview
@Composable
fun CardNoteYellowPreview() {
  CardNote(
    note = fakeNotesList[1],
    onClick = {},
  )
}

@Preview
@Composable
fun CardNoteOrangePreview() {
  CardNote(
    note = fakeNotesList[2],
    onClick = {},
  )
}

@Preview
@Composable
fun CardNotePurplePreview() {
  CardNote(
    note = fakeNotesList[3],
    onClick = {},
  )
}

@Preview
@Composable
fun CardNoteGreenPreview() {
  CardNote(
    note = fakeNotesList[4],
    onClick = {},
  )
}

@Preview
@Composable
fun CardNoteBluePreview() {
  CardNote(
    note = fakeNotesList[5],
    onClick = {},
  )
}

package com.github.crisacm.ui.screens.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.crisacm.ui.theme.GrayDarkBackground
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_github
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserAccountDialog(
  onLeftButtonClick: () -> Unit,
  onRightButtonClick: () -> Unit,
) {
  Dialog(onDismissRequest = {}) {
    CustomDialogUI(
      onLeftButtonClick = onLeftButtonClick,
      onRightButtonClick = onRightButtonClick,
    )
  }
}

@Composable
fun CustomDialogUI(
  modifier: Modifier = Modifier,
  onLeftButtonClick: () -> Unit,
  onRightButtonClick: () -> Unit,
) {
  Card(
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
  ) {
    Column(modifier.background(GrayDarkBackground)) {
      Image(
        painterResource(Res.drawable.ic_github),
        contentDescription = "github icon",
        contentScale = ContentScale.Fit,
        colorFilter =
          ColorFilter.tint(
            color = Color.Gray,
          ),
        modifier =
          Modifier
            .padding(top = 35.dp)
            .height(70.dp)
            .fillMaxWidth(),
      )

      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Hi! 👋🏻",
          textAlign = TextAlign.Center,
          modifier =
            Modifier
              .padding(top = 5.dp)
              .fillMaxWidth(),
          style = MaterialTheme.typography.labelLarge,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
        )
        Text(
          text =
            "This project is made with a lot of love by me, I hope you like it, I invite you to check " +
              "some of my other projects on my GitHub profile",
          textAlign = TextAlign.Center,
          modifier =
            Modifier
              .padding(top = 10.dp, start = 25.dp, end = 25.dp)
              .fillMaxWidth(),
          style = MaterialTheme.typography.bodyMedium,
        )
      }
      Row(
        Modifier
          .fillMaxWidth()
          .padding(top = 10.dp)
          .background(GrayDarkBackground),
        horizontalArrangement = Arrangement.SpaceAround,
      ) {
        TextButton(onClick = { onLeftButtonClick() }) {
          Text(
            "Go to GitHub",
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
          )
        }
        TextButton(onClick = { onRightButtonClick() }) {
          Text(
            "Dismiss",
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
          )
        }
      }
    }
  }
}

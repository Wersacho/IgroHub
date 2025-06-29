package com.example.app.ui.comments_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.app.R
import com.example.app.ui.details_screen.DetailsScreenViewModel
import com.example.app.ui.details_screen.data.DetailsNavObject
import com.example.app.ui.details_screen.data.RatingData
import com.example.app.ui.details_screen.ui.CommentDialog
import com.example.app.ui.details_screen.ui.CommentListItem
import com.example.app.ui.details_screen.ui.RateDialog
import com.example.app.ui.theme.LightRed

@SuppressLint("DefaultLocale")
@Composable
fun CommentsScreen(
    navObject: CommentsNavData = CommentsNavData(),

    viewModel: DetailsScreenViewModel = hiltViewModel()
) {

    var ratingDataToShow by remember { mutableStateOf(RatingData()) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getGameComments(navObject.gameId)
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = navObject.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (navObject.ratingsList.isEmpty()) {
                    "--"
                } else {
                    String.format("%.1f", navObject.ratingsList.average())
                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFC107)
            )
            Text(
                text = if (navObject.ratingsList.isEmpty()) {
                    " (0)"
                } else {
                    " (${navObject.ratingsList.size})"
                },
                color = Color.Gray,
                fontSize = 16.sp
            )
        }

        if (viewModel.commentsState.value.isNotEmpty()) {

            Text(text = "Комментарии", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.commentsState.value) { RatingData ->

                    Spacer(modifier = Modifier.height(8.dp))

                    CommentListItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                        },
                        ratingData = RatingData
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }

    }
}

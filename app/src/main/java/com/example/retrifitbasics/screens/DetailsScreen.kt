package com.example.retrifitbasics.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.retrifitbasics.MarsViewModel

@Composable
fun DetailsScreen(
    propertyIndex: String?,
    navController: NavController,
    marsViewModel: MarsViewModel
) {

    val property = marsViewModel.getProperty(propertyIndex?.toInt() ?: 0)

    // TODO: Implement the URL redirection in the view model

    // URL of the image at the endpoint is getting redirected,
    // so fixed the URL before showing the image. Otherwise, mo image is shown.
    val redirectedImgSrcUrl =
        property?.imgSrcUrl?.replace( // replace part of a string
            "http://mars.jpl.nasa.gov",
            "https://mars.nasa.gov"
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = rememberImagePainter(data = redirectedImgSrcUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        Text("Property id: ${property?.id}")
        Text("$ ${property?.price}")
        Text("For ${property?.type?.capitalize()}")
    }
}
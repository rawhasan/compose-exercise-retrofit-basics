package com.example.retrifitbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitBasicsApp()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun RetrofitBasicsApp(overviewViewModel: OverviewViewModel = viewModel()) {
    val status by overviewViewModel.status.observeAsState(MarsApiStatus.ERROR)
    val properties by overviewViewModel.properties.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Text(text = response, modifier = Modifier.padding(start = 4.dp, bottom = 8.dp))

        when (status) {
            MarsApiStatus.DONE -> {
                Log.d("RetrofitBasicsApp", "DONE")
                // TODO: Change the grid cell width to 50% of the screen width
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 140.dp)
                ) {
                    itemsIndexed(properties) { index, property ->

                        // URL of the image at the endpoint is getting redirected,
                        // so fixed the URL before showing the image. Otherwise, mo image is shown.
                        val redirectedImgSrcUrl =
                            property.imgSrcUrl.replace(
                                "http://mars.jpl.nasa.gov",
                                "https://mars.nasa.gov"
                            )

                        Row(modifier = Modifier.padding(4.dp)) {
                            Image(
                                painter = rememberImagePainter(
                                    data = redirectedImgSrcUrl,
                                    builder = {
                                        placeholder(R.drawable.loading_animation)
                                        error(R.drawable.ic_broken_image)
                                    }
                                ),
                                contentDescription = property.id,
                                modifier = Modifier.height(120.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            MarsApiStatus.LOADING -> {
                Log.d("RetrofitBasicsApp", "LOADING")
                Image(
                    painter = rememberImagePainter(data = R.drawable.loading_animation),
                    contentDescription = "Loading Properties",
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                Log.d("RetrofitBasicsApp", "ERROR (Else)")
                Image(
                    painter = rememberImagePainter(data = R.drawable.ic_connection_error),
                    contentDescription = "Connection Error",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitBasicsApp()
}
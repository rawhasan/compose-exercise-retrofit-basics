package com.example.retrifitbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.retrifitbasics.network.MarsProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitBasicsApp()
        }
    }
}

@Composable
fun RetrofitBasicsApp(overviewViewModel: OverviewViewModel = viewModel()) {
    val response by overviewViewModel.response.observeAsState("")
    val property by overviewViewModel.property.observeAsState(MarsProperty("", "", 0.0, ""))
    Log.d("RetrofitBasicsApp", "property: $property")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = response)

        // URL of the image at the endpoint is getting redirected,
        // so fixed the URL before showing the image. Otherwise, mo image is shown.
        val redirectedImgSrcUrl =
            property.imgSrcUrl.replace("http://mars.jpl.nasa.gov", "https://mars.nasa.gov")
        Log.d("RetrofitBasicsApp", "replaced URL: $redirectedImgSrcUrl")

        Image(
            painter = rememberImagePainter(data = redirectedImgSrcUrl),
            contentDescription = property.id,
            modifier = Modifier
                .padding(top = 16.dp)
                .weight(1f),
            contentScale = ContentScale.FillHeight
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitBasicsApp()
}
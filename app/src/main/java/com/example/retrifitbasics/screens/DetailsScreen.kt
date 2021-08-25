package com.example.retrifitbasics.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.retrifitbasics.MarsViewModel
import com.example.retrifitbasics.redirectedImgSrcUrl
import java.text.NumberFormat
import java.util.*

@Composable
fun DetailsScreen(
    propertyIndex: String?,
    marsViewModel: MarsViewModel,
    onSetTitle: (String) -> Unit,
    onShowDropdownMenu: (Boolean) -> Unit
) {
    val property = marsViewModel.getProperty(propertyIndex?.toInt() ?: 0)

    LaunchedEffect(Unit) {
        onSetTitle("Property # ${property?.id}")
        onShowDropdownMenu(false)
    }

    // currency format from number
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    val formattedPrice = format.format(property?.price)

    // get color from hex code
    val marsColor = Color(android.graphics.Color.parseColor("#9a7d55")) // color from hex

    val propertyType = property?.type
        ?.replace("buy", "Sell")
        ?.replace("rent", "Rent")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = rememberImagePainter(data = property?.let { redirectedImgSrcUrl(it.imgSrcUrl) }),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        Text("Property id: ${property?.id}")
        Text(
            formattedPrice,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = marsColor
        )
        Text(
            "For $propertyType",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(top = 8.dp)
                .background(marsColor)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            color = Color.White
        )
    }
}

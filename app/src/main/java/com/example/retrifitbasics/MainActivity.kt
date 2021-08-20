package com.example.retrifitbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.example.retrifitbasics.screens.HomeScreen

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
fun RetrofitBasicsApp() {
    HomeScreen()
}
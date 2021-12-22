package com.androidclasses.mycompozeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MessageCard("Android")
            MessageCard("Android")
        }
    }


    @Composable
    fun MessageCard(name:String) {
        Text(text = "Hello, $name!")
    }


    @Preview("My Fist Compose App", "", 21,
        -1,  -1, "", 2f, false)
    @Composable
    fun DefaultPreview() {
            MessageCard("Android 1.0")
    }

}

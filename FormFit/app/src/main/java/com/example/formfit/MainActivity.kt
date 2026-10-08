package com.example.formfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.formfit.theme.CanvasBackground
import com.example.formfit.theme.FormFitTheme
import com.example.formfit.ui.MainContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormFitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CanvasBackground
                ) {
                    MainContainer()
                }
            }
        }
    }
}

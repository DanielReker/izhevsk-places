package io.github.danielreker.izhevskplaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.danielreker.izhevskplaces.ui.CityApp
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IzhevskPlacesTheme {
                CityApp()
            }
        }
    }
}



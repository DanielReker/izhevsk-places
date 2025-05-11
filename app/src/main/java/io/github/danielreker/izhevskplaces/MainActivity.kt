package io.github.danielreker.izhevskplaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.danielreker.izhevskplaces.ui.city.CityScreen
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IzhevskPlacesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        CityApp()
                    }
                }
            }
        }
    }
}

@Serializable
object CityRoute


@Composable
fun CityApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CityRoute) {
        composable<CityRoute> {
            CityScreen()
        }
    }
}

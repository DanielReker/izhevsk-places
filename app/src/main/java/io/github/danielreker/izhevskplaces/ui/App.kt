package io.github.danielreker.izhevskplaces.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.danielreker.izhevskplaces.ui.category.CategoryScreen
import io.github.danielreker.izhevskplaces.ui.city.CityScreen
import io.github.danielreker.izhevskplaces.ui.recommendation.RecommendationScreen
import kotlinx.serialization.Serializable


@Serializable
object CityRoute

@Serializable
data class CategoryRoute(val categoryId: String)

@Serializable
data class RecommendationRoute(val recommendationId: String)


@Composable
fun CityApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CityRoute,
    ) {
        composable<CityRoute> {
            CityScreen(
                onCategorySelected = { categoryId -> navController.navigate(CategoryRoute(categoryId)) },
            )
        }
        composable<CategoryRoute> {
            CategoryScreen(
                onRecommendationSelected = { recommendationId -> navController.navigate(RecommendationRoute(recommendationId)) },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable<RecommendationRoute> {
            RecommendationScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
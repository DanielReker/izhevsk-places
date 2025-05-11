package io.github.danielreker.izhevskplaces.ui.category

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.danielreker.izhevskplaces.CityAppBar
import io.github.danielreker.izhevskplaces.CityAppScreen
import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme


@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    onRecommendationSelected: (recommendationId: String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CityAppScreen(
        appBar = { CityAppBar(
            title = uiState.category?.name,
            canNavigateBack = true,
            navigateUp = onNavigateUp,
        ) }
    ) {
        CategoryScreenUi(uiState = uiState, modifier = modifier, onRecommendationSelected = onRecommendationSelected)
    }
}

@Composable
fun CategoryScreenUi(
    uiState: CategoryUiState,
    modifier: Modifier = Modifier,
    onRecommendationSelected: (recommendationId: String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
    ) {
        if (uiState.recommendations != null) {
            items(uiState.recommendations) { recommendation ->
                Card(
                    onClick = { onRecommendationSelected(recommendation.id) },
                    modifier = Modifier.padding(8.dp).fillParentMaxWidth()
                ) {
                    Text(
                        text = recommendation.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            item { CircularProgressIndicator() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    IzhevskPlacesTheme {
        CategoryScreenUi(uiState = CategoryUiState(
            category = CityProvider.getCategoryById("parks"),
            recommendations = CityProvider.getRecommendationsByCategory("parks"),
        ))
    }
}


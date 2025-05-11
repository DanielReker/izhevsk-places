package io.github.danielreker.izhevskplaces.ui.recommendation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import io.github.danielreker.izhevskplaces.ui.CityAppBar
import io.github.danielreker.izhevskplaces.ui.CityAppScreen
import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme


@Composable
fun RecommendationScreen(
    modifier: Modifier = Modifier,
    viewModel: RecommendationViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CityAppScreen(
        appBar = { CityAppBar(
            title = uiState.recommendation?.name,
            canNavigateBack = true,
            navigateUp = onNavigateUp,
        ) }
    ) {
        RecommendationScreenUi(uiState = uiState, modifier = modifier)
    }
}

@Composable
fun RecommendationScreenUi(
    uiState: RecommendationUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        if (uiState.recommendation != null) {
            Image(
                painter = painterResource(LocalContext.current.resources.getIdentifier(uiState.recommendation.imageSlug, "drawable", LocalContext.current.packageName)),
                contentDescription = uiState.recommendation.imageSlug,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(uiState.recommendation.description)
        } else {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationScreenPreview() {
    IzhevskPlacesTheme {
        RecommendationScreenUi(uiState = RecommendationUiState(
            recommendation = CityProvider.getRecommendationById("park-gorkogo")
        ))
    }
}
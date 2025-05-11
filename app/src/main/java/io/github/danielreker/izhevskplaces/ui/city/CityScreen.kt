package io.github.danielreker.izhevskplaces.ui.city

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme


@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    viewModel: CityViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CityScreenUi(uiState = uiState, modifier = modifier)
}

@Composable
fun CityScreenUi(
    uiState: CityUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = uiState.toString(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityScreenPreview() {
    IzhevskPlacesTheme {
        CityScreenUi(uiState = CityUiState(CityProvider.getCity(), categories = CityProvider.getAllCategories()))
    }
}
package io.github.danielreker.izhevskplaces.ui.city

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
import io.github.danielreker.izhevskplaces.ui.CityAppBar
import io.github.danielreker.izhevskplaces.ui.CityAppScreen
import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.ui.theme.IzhevskPlacesTheme


@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    viewModel: CityViewModel = hiltViewModel(),
    onCategorySelected: (categoryId: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CityAppScreen(
        appBar = { CityAppBar(
            title = uiState.city?.name,
            canNavigateBack = false,
            navigateUp = {},
        ) }
    ) {
        CityScreenUi(uiState = uiState, modifier = modifier, onCategorySelected = onCategorySelected)
    }
}

@Composable
fun CityScreenUi(
    uiState: CityUiState,
    modifier: Modifier = Modifier,
    onCategorySelected: (categoryId: String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
    ) {
        if (uiState.categories != null) {
            items(uiState.categories) { category ->
                Card(
                    onClick = { onCategorySelected(category.id) },
                    modifier = Modifier.padding(8.dp).fillParentMaxWidth()
                ) {
                    Text(
                        text = category.name,
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
fun CityScreenPreview() {
    IzhevskPlacesTheme {
        CityScreenUi(uiState = CityUiState(CityProvider.getCity(), categories = CityProvider.getAllCategories()))
    }
}


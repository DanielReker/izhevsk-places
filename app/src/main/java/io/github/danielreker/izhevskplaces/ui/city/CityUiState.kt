package io.github.danielreker.izhevskplaces.ui.city

import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.City

data class CityUiState(
    val city: City? = null,
    val categories: List<Category>? = null,
)
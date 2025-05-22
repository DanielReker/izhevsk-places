package io.github.danielreker.izhevskplaces.ui.category

import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.Recommendation

data class CategoryUiState(
    val category: Category? = null,
    val recommendations: List<Recommendation>? = null,
)
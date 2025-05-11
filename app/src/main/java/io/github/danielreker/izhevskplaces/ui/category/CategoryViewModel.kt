package io.github.danielreker.izhevskplaces.ui.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.danielreker.izhevskplaces.CategoryRoute
import io.github.danielreker.izhevskplaces.data.repositories.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val cityRepository: CityRepository,
) : ViewModel() {
    private val categoryId: String = savedStateHandle.toRoute<CategoryRoute>().categoryId

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cityRepository.getCategory(categoryId).collect{ category ->
                _uiState.update { uiState ->
                    uiState.copy(category = category)
                }
            }
            cityRepository.getRecommendationsForCategory(categoryId).collect{ recommendations ->
                _uiState.update { uiState ->
                    uiState.copy(recommendations = recommendations)
                }
            }
        }
    }
}
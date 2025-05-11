package io.github.danielreker.izhevskplaces.ui.recommendation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.danielreker.izhevskplaces.RecommendationRoute
import io.github.danielreker.izhevskplaces.data.repositories.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val cityRepository: CityRepository,
) : ViewModel() {
    private val recommendationId: String = savedStateHandle.toRoute<RecommendationRoute>().recommendationId

    private val _uiState = MutableStateFlow(RecommendationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cityRepository.getRecommendation(recommendationId).collect{ recommendation ->
                _uiState.update { uiState ->
                    uiState.copy(recommendation = recommendation)
                }
            }
        }
    }
}
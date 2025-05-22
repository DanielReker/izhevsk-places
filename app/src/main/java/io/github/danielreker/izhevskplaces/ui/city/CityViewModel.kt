package io.github.danielreker.izhevskplaces.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.danielreker.izhevskplaces.data.repositories.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityRepository: CityRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cityRepository.getCity().collect { city ->
                _uiState.update { uiState ->
                    uiState.copy(city = city)
                }
            }
            cityRepository.getCategories().collect { categories ->
                _uiState.update { uiState ->
                    uiState.copy(categories = categories)
                }
            }
        }
    }
}
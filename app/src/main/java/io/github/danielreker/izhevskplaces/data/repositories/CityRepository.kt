package io.github.danielreker.izhevskplaces.data.repositories

import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.City
import io.github.danielreker.izhevskplaces.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getCity(): Flow<City>

    suspend fun getCategories(): Flow<List<Category>>

    suspend fun getRecommendationsForCategory(categoryId: String): Flow<List<Recommendation>>

    suspend fun getRecommendation(recommendationId: String): Flow<Recommendation?>
}
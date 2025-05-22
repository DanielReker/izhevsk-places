package io.github.danielreker.izhevskplaces.data.repositories

import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.model.City
import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.Recommendation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CityRepositoryImpl @Inject constructor(
    private val cityProvider: CityProvider,
) : CityRepository {

    override suspend fun getCity(): Flow<City> = flowOf(cityProvider.getCity())

    override suspend fun getCategories(): Flow<List<Category>> =
        flowOf(cityProvider.getAllCategories())

    override suspend fun getCategory(categoryId: String): Flow<Category?> =
        flowOf(cityProvider.getCategoryById(categoryId))

    override suspend fun getRecommendationsForCategory(categoryId: String): Flow<List<Recommendation>> =
        flowOf(cityProvider.getRecommendationsByCategory(categoryId))

    override suspend fun getRecommendation(recommendationId: String): Flow<Recommendation?> =
        flowOf(cityProvider.getRecommendationById(recommendationId))
}
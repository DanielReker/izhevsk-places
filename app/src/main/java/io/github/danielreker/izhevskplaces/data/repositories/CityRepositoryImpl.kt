package io.github.danielreker.izhevskplaces.data.repositories

import io.github.danielreker.izhevskplaces.data.datasources.CityProvider
import io.github.danielreker.izhevskplaces.model.City
import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.Recommendation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CityRepositoryImpl @Inject constructor(
    private val cityProvider: CityProvider,
) : CityRepository {

    override suspend fun getCity(): Flow<City> = flow {
        emit(cityProvider.getCity())
    }.flowOn(Dispatchers.IO)

    override suspend fun getCategories(): Flow<List<Category>> = flow {
        emit(cityProvider.getAllCategories())
    }.flowOn(Dispatchers.IO)

    override suspend fun getCategory(categoryId: String): Flow<Category?> = flow {
        emit(cityProvider.getCategoryById(categoryId))
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecommendationsForCategory(categoryId: String): Flow<List<Recommendation>> = flow {
        emit(cityProvider.getRecommendationsByCategory(categoryId))
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecommendation(recommendationId: String): Flow<Recommendation?> = flow {
        emit(cityProvider.getRecommendationById(recommendationId))
    }.flowOn(Dispatchers.IO)
}
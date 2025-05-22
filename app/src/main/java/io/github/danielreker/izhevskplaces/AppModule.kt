package io.github.danielreker.izhevskplaces

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.danielreker.izhevskplaces.data.repositories.CityRepository
import io.github.danielreker.izhevskplaces.data.repositories.CityRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository
}
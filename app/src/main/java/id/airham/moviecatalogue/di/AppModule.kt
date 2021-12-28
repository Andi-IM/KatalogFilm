package id.airham.moviecatalogue.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.id.airham.core.domain.usecase.CatalogueUseCase
import my.id.airham.core.utils.CatalogInteractor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideCatalogueUseCase(catalogInteractor: CatalogInteractor): CatalogueUseCase
}
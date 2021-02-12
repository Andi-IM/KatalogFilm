package id.airham.moviecatalogue.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.airham.moviecatalogue.data.source.CatalogueDataSource
import id.airham.moviecatalogue.data.source.CatalogueRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(catalogueRepository: CatalogueRepository): CatalogueDataSource
}
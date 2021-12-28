package my.id.airham.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.id.airham.core.data.CatalogueRepository
import my.id.airham.core.domain.repository.ICatalogueRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(catalogueRepository: CatalogueRepository): ICatalogueRepository
}
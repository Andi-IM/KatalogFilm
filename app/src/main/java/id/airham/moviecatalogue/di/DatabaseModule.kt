package id.airham.moviecatalogue.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.airham.moviecatalogue.data.source.local.room.CatalogueDao
import id.airham.moviecatalogue.data.source.local.room.CatalogueDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): CatalogueDatabase {
        return Room.databaseBuilder(
            appContext,
            CatalogueDatabase::class.java,
            "catalogue.db"
        ).build()
    }

    @Provides
    fun provideDao(database: CatalogueDatabase): CatalogueDao =
        database.catalogueDao()

}
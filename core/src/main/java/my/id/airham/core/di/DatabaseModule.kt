package my.id.airham.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.id.airham.core.data.source.local.room.CatalogueDao
import my.id.airham.core.data.source.local.room.CatalogueDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): CatalogueDatabase = Room.databaseBuilder(
        appContext,
        CatalogueDatabase::class.java, "catalogue.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDao(database: CatalogueDatabase): CatalogueDao = database.catalogueDao()
}
package id.airham.moviecatalogue.di

import android.content.Context
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.room.CatalogueDatabase
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.utils.AppExecutors
import id.airham.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : CatalogueRepository{
       val database = CatalogueDatabase.getInstance(context)

        val remoteRepository = RemoteRepository.getInstance(JsonHelper(context))
        val localRepository = LocalRepository.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteRepository, localRepository, appExecutors)
    }
}
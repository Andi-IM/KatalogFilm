package id.airham.moviecatalogue.di

import android.content.Context
import id.airham.moviecatalogue.data.source.ItemRepository
import id.airham.moviecatalogue.data.source.remote.RemoteDataSource
import id.airham.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : ItemRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return ItemRepository.getInstance(remoteDataSource)
    }
}
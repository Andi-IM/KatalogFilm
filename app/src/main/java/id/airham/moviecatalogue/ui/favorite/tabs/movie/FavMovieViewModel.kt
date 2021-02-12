package id.airham.moviecatalogue.ui.favorite.tabs.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import javax.inject.Inject

class FavMovieViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getFavorites(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoritedMovies()
}
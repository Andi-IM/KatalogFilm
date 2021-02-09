package id.airham.moviecatalogue.ui.favorite.tabs.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity

class FavMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavorites(): LiveData<List<MovieEntity>> = catalogueRepository.getFavoritedMovies()
}
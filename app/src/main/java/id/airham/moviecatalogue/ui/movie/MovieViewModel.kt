package id.airham.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.source.ItemRepository

/**
 *  Kelas ini merupakan viewmodel dari MovieFragment
 *  berisi fungsi getMovie yang mendapatkan data dari DataDummy.generateMovies()
 */

class MovieViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = itemRepository.getAllMoviesOffline()
}


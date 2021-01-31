package id.airham.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.utils.DataDummy

/**
 *  Kelas ini merupakan viewmodel dari MovieFragment
 *  berisi fungsi getMovie yang mendapatkan data dari DataDummy.generateMovies()
 */

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateMovies()
}
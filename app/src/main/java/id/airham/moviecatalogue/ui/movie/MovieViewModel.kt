package id.airham.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.vo.Resource

/**
 *  Kelas ini merupakan viewmodel dari MovieFragment
 *  berisi fungsi getMovie yang mendapatkan data dari DataDummy.generateMovies()
 */

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> =
        catalogueRepository.getAllMovies()
}


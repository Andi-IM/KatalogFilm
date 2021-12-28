package id.airham.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

/**
 *  Kelas ini merupakan viewmodel dari MovieFragment
 *  berisi fungsi getMovie yang mendapatkan data dari DataDummy.generateMovies()
 */

@HiltViewModel
class MovieViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    val movies = catalogueUseCase.getAllMovies().asLiveData()
}


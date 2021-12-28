package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.data.Resource
import my.id.airham.core.domain.model.Movie
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getMovie} yang akan mengembalikan data berupa
 *  Entitas Movie.
 */
@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    private var itemId = MutableLiveData<Int>()
    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var movie: LiveData<Resource<Movie>> =
        Transformations.switchMap(itemId) { mId ->
            catalogueUseCase.getMovie(mId).asLiveData()
        }

    fun setFavoriteMovie() {
        val movie = movie.value
        if (movie != null){
            val movieData = movie.data

            movieData?.let {
                catalogueUseCase.setMovieFavorite(it, !it.favorited)
            }
        }
    }
}
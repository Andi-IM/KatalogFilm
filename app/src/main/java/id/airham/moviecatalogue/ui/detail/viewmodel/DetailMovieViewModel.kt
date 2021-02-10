package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.vo.Resource

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getMovie} yang akan mengembalikan data berupa
 *  Entitas Movie.
 */
class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private var itemId = MutableLiveData<Int>()
    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(itemId) { mId ->
            catalogueRepository.getMovie(mId)
        }

    fun setFavorite() {
        val movie = movie.value
        if (movie != null){
            val movieData = movie.data

            movieData?.let {
                catalogueRepository.setMovieFavorite(it, !it.favorited)
            }
        }
    }
}
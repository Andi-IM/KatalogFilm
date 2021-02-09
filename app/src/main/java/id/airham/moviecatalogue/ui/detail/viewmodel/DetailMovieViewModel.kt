package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.vo.Resource
import kotlin.properties.Delegates

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getMovie} yang akan mengembalikan data berupa
 *  Entitas Movie.
 */

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private var itemId by Delegates.notNull<Int>()
    fun setSelectedItem(itemId: Int) {
        this.itemId = itemId
    }

    fun getMovie(): LiveData<Resource<MovieEntity>> = catalogueRepository.getMovie(itemId)
    fun setFavorite() {

    }
}
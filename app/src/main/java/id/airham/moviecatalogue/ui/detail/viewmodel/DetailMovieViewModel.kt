package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.source.ItemRepository

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getMovie} yang akan mengembalikan data berupa
 *  Entitas Movie.
 */

class DetailMovieViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    private lateinit var itemId: String
    fun setSelectedItem(itemId: String) {
        this.itemId = itemId
    }

    fun getMovie(): LiveData<MovieEntity> = itemRepository.getMovieOffline(itemId)
}
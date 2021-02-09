package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.vo.Resource
import kotlin.properties.Delegates

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getTvShow} yang akan mengembalikan data berupa
 *  Entitas TvShows.
 */

class DetailTvViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private var itemId : Int = 0
    fun setSelectedItem(itemId: Int) {
        this.itemId = itemId
    }

    fun getTvShow(): LiveData<Resource<TvShowEntity>> = catalogueRepository.getTvShow(itemId)
    fun setFavorite() {

    }
}
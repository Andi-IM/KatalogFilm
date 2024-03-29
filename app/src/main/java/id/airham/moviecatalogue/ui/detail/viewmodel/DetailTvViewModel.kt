package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.vo.Resource

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getTvShow} yang akan mengembalikan data berupa
 *  Entitas TvShows.
 */
class DetailTvViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private var itemId = MutableLiveData<Int>()
    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var tvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(itemId) { mId ->
            catalogueRepository.getTvShow(mId)
        }

    fun setFavorite() {
        val tvShow = tvShow.value
        if (tvShow != null) {
            val tvShowData = tvShow.data

            tvShowData?.let {
                catalogueRepository.setTvShowFavorite(it, !it.favorited)
            }
        }
    }
}
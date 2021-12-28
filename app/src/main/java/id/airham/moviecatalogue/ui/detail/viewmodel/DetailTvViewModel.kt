package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.data.Resource
import my.id.airham.core.domain.model.TvShow
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getTvShow} yang akan mengembalikan data berupa
 *  Entitas TvShows.
 */
@HiltViewModel
class DetailTvViewModel @Inject constructor(private val catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    private var itemId = MutableLiveData<Int>()
    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var tvShow: LiveData<Resource<TvShow>> =
        Transformations.switchMap(itemId) { mId ->
            catalogueUseCase.getTvShow(mId).asLiveData()
        }

    fun setFavoriteTvShow() {
        val tvShow = tvShow.value
        if (tvShow != null){
            val tvShowData = tvShow.data

            tvShowData?.let {
                catalogueUseCase.setTvShowFavorite(it, !it.favorited)
            }
        }
    }
}
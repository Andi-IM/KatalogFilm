package id.airham.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.data.source.ItemRepository

/**
 *  Kelas ini merupakan viewmodel dari TvShowFragment
 *  berisi fungsi getTvShow yang mendapatkan data dari DataDummy.generateTvs()
 */

class TvShowViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = itemRepository.getAllTvShowsOffline()
}
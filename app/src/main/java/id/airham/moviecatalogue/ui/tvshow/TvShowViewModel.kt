package id.airham.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.utils.DataDummy

/**
 *  Kelas ini merupakan viewmodel dari TvShowFragment
 *  berisi fungsi getTvShow yang mendapatkan data dari DataDummy.generateTvs()
 */

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<TvShowEntity> = DataDummy.generateTvs()
}
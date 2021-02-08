package id.airham.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.vo.Resource

/**
 *  Kelas ini merupakan viewmodel dari TvShowFragment
 *  berisi fungsi getTvShow yang mendapatkan data dari DataDummy.generateTvs()
 */

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> = catalogueRepository.getAllTvShows()
}
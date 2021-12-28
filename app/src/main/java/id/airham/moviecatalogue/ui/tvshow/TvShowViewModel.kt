package id.airham.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

/**
 *  Kelas ini merupakan viewmodel dari TvShowFragment
 *  berisi fungsi getTvShow yang mendapatkan data dari DataDummy.generateTvs()
 */

@HiltViewModel
class TvShowViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
        val tvShows = catalogueUseCase.getAllTvShows().asLiveData()
}
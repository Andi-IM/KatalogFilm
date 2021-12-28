package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

@HiltViewModel
class FavTvViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    val favTvShow = catalogueUseCase.getAllTvShows().asLiveData()
}
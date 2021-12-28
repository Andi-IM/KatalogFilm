package id.airham.moviecatalogue.ui.favorite.tabs.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
        val favMovie = catalogueUseCase.getFavoritedMovies().asLiveData()
}
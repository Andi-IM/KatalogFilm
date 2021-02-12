package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavTvViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getFavorites(): LiveData<PagedList<TvShowEntity>> =
        catalogueRepository.getFavoritedTvShows()
}
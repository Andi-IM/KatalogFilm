package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity

class FavTvViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavorites(): LiveData<PagedList<TvShowEntity>> =
        catalogueRepository.getFavoritedTvShows()

    fun setFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorited
        catalogueRepository.setTvShowFavorite(tvShowEntity, newState)
    }
}
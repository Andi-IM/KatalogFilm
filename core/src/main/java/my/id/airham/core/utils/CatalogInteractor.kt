package my.id.airham.core.utils

import androidx.paging.PagedList
import kotlinx.coroutines.flow.Flow
import my.id.airham.core.data.Resource
import my.id.airham.core.domain.model.Movie
import my.id.airham.core.domain.model.TvShow
import my.id.airham.core.domain.repository.ICatalogueRepository
import my.id.airham.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

class CatalogInteractor @Inject constructor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    override fun getAllMovies(): Flow<Resource<PagedList<Movie>>> = catalogueRepository.getAllMovies()
    override fun getAllTvShows(): Flow<Resource<PagedList<TvShow>>> = catalogueRepository.getAllTvShows()
    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        catalogueRepository.setMovieFavorite(movie, state)
    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, state)
    override fun getMovie(id: Int): Flow<Resource<Movie>> = catalogueRepository.getMovie(id)
    override fun getTvShow(id: Int): Flow<Resource<TvShow>> = catalogueRepository.getTvShow(id)
    override fun getFavoritedMovies(): Flow<PagedList<Movie>> = catalogueRepository.getFavoritedMovies()
    override fun getFavoritedTvShows(): Flow<PagedList<TvShow>> =
        catalogueRepository.getFavoritedTvShows()
}
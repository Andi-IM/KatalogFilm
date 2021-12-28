package my.id.airham.core.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import my.id.airham.core.data.Resource
import my.id.airham.core.domain.model.Movie
import my.id.airham.core.domain.model.TvShow

interface ICatalogueRepository {
    fun getAllMovies(): Flow<Resource<PagingData<Movie>>>
    fun getAllTvShows(): Flow<Resource<PagingData<TvShow>>>
    fun setMovieFavorite(movie: Movie, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)
    fun getMovie(id: Int): Flow<Resource<Movie>>
    fun getTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoritedMovies(): Flow<PagingData<Movie>>
    fun getFavoritedTvShows(): Flow<PagingData<TvShow>>
}
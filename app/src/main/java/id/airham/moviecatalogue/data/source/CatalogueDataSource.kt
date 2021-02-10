package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.vo.Resource

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>>
}
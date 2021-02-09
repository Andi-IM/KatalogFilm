package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.vo.Resource

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoritedMovies(): LiveData<List<MovieEntity>>
    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>>
}
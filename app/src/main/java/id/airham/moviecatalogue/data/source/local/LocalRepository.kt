package id.airham.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.local.room.CatalogueDao

class LocalRepository @Inject constructor(private val mCatalogueDao: CatalogueDao) {

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovies()

    fun getFavoritedMovies(): DataSource.Factory<Int, MovieEntity> =
        mCatalogueDao.getFavoritedMovie()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mCatalogueDao.getMovieById(id)

    fun insertMovie(movie: List<MovieEntity>) = mCatalogueDao.insertMovies(movie)

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getTvShow()

    fun getFavoritedTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mCatalogueDao.getFavoritedTvShow()

    fun insertTvShow(tvShow: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShow)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowByid(id: Int): LiveData<TvShowEntity> =
        mCatalogueDao.getTvShowById(id)


}
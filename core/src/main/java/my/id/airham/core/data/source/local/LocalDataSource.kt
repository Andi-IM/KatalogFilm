package my.id.airham.core.data.source.local

import androidx.paging.DataSource
import kotlinx.coroutines.flow.Flow
import my.id.airham.core.data.source.local.entity.MovieEntity
import my.id.airham.core.data.source.local.entity.TvShowEntity
import my.id.airham.core.data.source.local.room.CatalogueDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mCatalogueDao: CatalogueDao) {
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> {
        return mCatalogueDao.getMovies()
    }
    fun getFavoritedMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getFavoritedMovie()
    fun getMovieById(id: Int): Flow<MovieEntity> = mCatalogueDao.getMovieById(id)
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
    fun getTvShowByid(id: Int): Flow<TvShowEntity> =
        mCatalogueDao.getTvShowById(id)
}
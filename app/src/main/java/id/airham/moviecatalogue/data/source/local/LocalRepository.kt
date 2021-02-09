package id.airham.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.local.room.CatalogueDao

class LocalRepository private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalRepository? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalRepository =
            INSTANCE ?: LocalRepository(catalogueDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mCatalogueDao.getMovies()

    fun getFavoritedMovies(): LiveData<List<MovieEntity>> = mCatalogueDao.getFavoritedMovie()

    fun getMovieById(id: Int): LiveData<MovieEntity> =
        mCatalogueDao.getMovieById(id)

    fun insertMovie(movie: List<MovieEntity>) = mCatalogueDao.insertMovies(movie)

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mCatalogueDao.getTvShow()

    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> = mCatalogueDao.getFavoritedTvShow()

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
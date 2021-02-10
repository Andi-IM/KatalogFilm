package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.ApiResponse
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem
import id.airham.moviecatalogue.utils.AppExecutors
import id.airham.moviecatalogue.vo.Resource

class CatalogueRepository private constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(
            remoteData: RemoteRepository,
            localData: LocalRepository,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localRepository.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteRepository.getAllMovies()

            override fun saveCallResult(data: List<MovieItem>) {
                val moviePagedList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.originalTitle,
                        response.overview,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        false
                    )
                    moviePagedList.add(movie)
                }
                localRepository.insertMovie(moviePagedList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localRepository.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> =
                remoteRepository.getAllTvShows()

            override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowPagedList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.originalName,
                        response.overview,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage,
                        false
                    )
                    tvShowPagedList.add(tvShow)
                }
                localRepository.insertTvShow(tvShowPagedList)
            }

        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localRepository.setMovieFavorite(movie, state) }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute { localRepository.setTvShowFavorite(tvShow, state) }
    }

    override fun getMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localRepository.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteRepository.getAllMovies()

            override fun saveCallResult(data: List<MovieItem>) {
                val moviePagedList = ArrayList<MovieEntity>()
                for (response in data) {
                    if (response.id == id) {
                        val movie = MovieEntity(
                            response.id,
                            response.originalTitle,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage,
                            false
                        )
                        moviePagedList.add(movie)
                    }
                }
                localRepository.insertMovie(moviePagedList)
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, List<TvShowItem>>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localRepository.getTvShowByid(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> =
                remoteRepository.getAllTvShows()

            override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowPagedList = ArrayList<TvShowEntity>()
                for (response in data) {
                   if (response.id == id){
                       val tvShow = TvShowEntity(
                           response.id,
                           response.originalName,
                           response.overview,
                           response.posterPath,
                           response.firstAirDate,
                           response.voteAverage,
                           false
                       )
                       tvShowPagedList.add(tvShow)
                   }
                }
                localRepository.insertTvShow(tvShowPagedList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localRepository.getFavoritedMovies(), config).build()
    }


    override fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localRepository.getFavoritedTvShows(), config).build()
    }

}
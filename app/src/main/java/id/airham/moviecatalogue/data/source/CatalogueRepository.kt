package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.data.source.remote.ApiResponse
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

    // ONLINE VERSION
    // NOT IN USE

    /*override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteRepository.getAllMovies(object : RemoteRepository.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id.toString(),
                        response.originalTitle,
                        response.overview,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteRepository.getAllTvShows(object : RemoteRepository.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse){
                    val tvShow = TvShowEntity(
                        response.id.toString(),
                        response.originalName,
                        response.overview,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }

        })
        return tvShowResults
    }

    fun getMovie(movieId : String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteRepository.getAllMovies(object : RemoteRepository.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieItem>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.id.toString() == movieId) {
                        movie = MovieEntity(
                            response.id.toString(),
                            response.originalTitle,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage
                        )
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    fun getTvShow(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteRepository.getAllTvShows(object : RemoteRepository.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowItem>) {
                lateinit var tvShow: TvShowEntity
                for (response in tvShowResponse) {
                    if (response.id.toString() == tvShowId){
                        tvShow = TvShowEntity(
                            response.id.toString(),
                            response.originalName,
                            response.overview,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage
                        )
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }*/

    // OFFLINE VERSION
    // untuk testing
    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localRepository.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteRepository.getAllMovies()

            override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
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
                    movieList.add(movie)
                }
                localRepository.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localRepository.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> =
                remoteRepository.getAllTvShows()

            override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
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
                    tvShowList.add(tvShow)
                }

                localRepository.insertTvShow(tvShowList)
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
                val movieList = ArrayList<MovieEntity>()
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
                        movieList.add(movie)
                    }
                }
                localRepository.insertMovie(movieList)
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
                val tvShowList = ArrayList<TvShowEntity>()
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
                       tvShowList.add(tvShow)
                   }
                }
                localRepository.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<List<MovieEntity>> =
        localRepository.getFavoritedMovies()

    override fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> =
        localRepository.getFavoritedTvShows()
}
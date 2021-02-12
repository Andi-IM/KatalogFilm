package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.ApiResponse
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.data.source.remote.response.GetMovieDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.GetTvDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.MovieResponse
import id.airham.moviecatalogue.data.source.remote.response.TvShowResponse
import id.airham.moviecatalogue.utils.AppExecutors
import id.airham.moviecatalogue.vo.Resource

class CatalogueRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {
    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                val localData = localRepository.getAllMovies()
                return LivePagedListBuilder(localData, config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteRepository.getAllMovies()

            override fun saveCallResult(data: MovieResponse) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data.results) {
                    val movie = MovieEntity(
                        response.id,
                        response.originalTitle,
                        response.overview,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        false,
                        response.popularity
                    )
                    movieList.add(movie)
                }
                localRepository.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                val localData = localRepository.getAllTvShows()
                return LivePagedListBuilder(localData, config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteRepository.getAllTvShows()

            override fun saveCallResult(data: TvShowResponse) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data.results) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.originalName,
                        response.overview,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage,
                        false,
                        response.popularity
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
        return object : NetworkBoundResource<MovieEntity, GetMovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localRepository.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<GetMovieDetailResponse>> =
                remoteRepository.getMovieDetail(id)

            override fun saveCallResult(data: GetMovieDetailResponse) {
                val movies = ArrayList<MovieEntity>()
                val movie = MovieEntity(
                    data.id,
                    data.originalTitle,
                    data.overview,
                    data.posterPath,
                    data.releaseDate,
                    data.voteAverage,
                    false,
                    data.popularity
                )
                movies.add(movie)
                localRepository.insertMovie(movies)
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, GetTvDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localRepository.getTvShowByid(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<GetTvDetailResponse>> =
                remoteRepository.getTvShowDetail(id)

            override fun saveCallResult(data: GetTvDetailResponse) {
                val shows = ArrayList<TvShowEntity>()
                val tvShow = TvShowEntity(
                    data.id,
                    data.originalName,
                    data.overview,
                    data.posterPath,
                    data.firstAirDate,
                    data.voteAverage,
                    false
                )
                shows.add(tvShow)
                localRepository.insertTvShow(shows)
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
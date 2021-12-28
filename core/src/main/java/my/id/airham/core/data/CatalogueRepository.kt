package my.id.airham.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import my.id.airham.core.data.source.local.LocalDataSource
import my.id.airham.core.data.source.local.entity.MovieEntity
import my.id.airham.core.data.source.local.entity.TvShowEntity
import my.id.airham.core.data.source.remote.RemoteDataSource
import my.id.airham.core.data.source.remote.network.ApiResponse
import my.id.airham.core.data.source.remote.response.GetMovieDetailResponse
import my.id.airham.core.data.source.remote.response.GetTvDetailResponse
import my.id.airham.core.data.source.remote.response.MovieResultsItem
import my.id.airham.core.data.source.remote.response.TvShowResultsItem
import my.id.airham.core.domain.model.Movie
import my.id.airham.core.domain.model.TvShow
import my.id.airham.core.domain.repository.ICatalogueRepository
import my.id.airham.core.utils.AppExecutors
import my.id.airham.core.utils.DataMapper
import javax.inject.Inject

class CatalogueRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {
    override fun getAllMovies(): Flow<Resource<PagingData<Movie>>> =
        object : NetworkBoundResource<PagingData<Movie>, List<MovieResultsItem>>() {
            override fun loadFromDB(): Flow<PagingData<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: PagingData<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResultsItem>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResultsItem>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getAllTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResultsItem>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResultsItem>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<TvShowResultsItem>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val entity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(entity, state) }
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val entity = DataMapper.mapTvDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(entity, state) }
    }

    override fun getMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, GetMovieDetailResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id)
                    .map { DataMapper.mapMovieEntityToDomain(it) }
            }

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<GetMovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: GetMovieDetailResponse) {
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
                localDataSource.insertMovie(movies)
            }
        }.asFlow()

    override fun getTvShow(id: Int): Flow<Resource<TvShow>> =
        object : NetworkBoundResource<TvShow, GetTvDetailResponse>() {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getTvShowByid(id)
                    .map { DataMapper.mapTvShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: TvShow?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<GetTvDetailResponse>> =
                remoteDataSource.getTvShowDetail(id)

            override suspend fun saveCallResult(data: GetTvDetailResponse) {
                val tvShows = ArrayList<TvShowEntity>()
                val tvShow = TvShowEntity(
                    data.id,
                    data.originalName,
                    data.overview,
                    data.posterPath,
                    data.firstAirDate,
                    data.voteAverage,
                    false,
                    data.popularity
                )
                tvShows.add(tvShow)
                localDataSource.insertTvShow(tvShows)
            }
        }.asFlow()

    override fun getFavoritedMovies(): Flow<PagedList<Movie>> =
        localDataSource.getFavoritedMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }

    override fun getFavoritedTvShows(): Flow<PagedList<TvShow>> =
        localDataSource.getFavoritedTvShows().map {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
}
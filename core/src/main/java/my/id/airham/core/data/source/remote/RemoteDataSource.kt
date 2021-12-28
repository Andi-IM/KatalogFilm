package my.id.airham.core.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.id.airham.core.data.source.remote.network.ApiResponse
import my.id.airham.core.data.source.remote.network.ApiService
import my.id.airham.core.data.source.remote.response.GetMovieDetailResponse
import my.id.airham.core.data.source.remote.response.GetTvDetailResponse
import my.id.airham.core.data.source.remote.response.MovieResultsItem
import my.id.airham.core.data.source.remote.response.TvShowResultsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResultsItem>>> {
        // get data from remote api
        return flow {
            try {
                val response = apiService.getAllMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShows(): Flow<ApiResponse<List<TvShowResultsItem>>> {
        // get tvShow from remote api
        return flow {
            try {
                val response = apiService.getAllTvShow()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMovieDetail(id: Int): Flow<ApiResponse<GetMovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovie(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTvShowDetail(id: Int): Flow<ApiResponse<GetTvDetailResponse>> {
        return flow {
            try {
                val response = apiService.getTVShow(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
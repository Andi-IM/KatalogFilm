package id.airham.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import id.airham.moviecatalogue.data.source.remote.network.ApiService
import id.airham.moviecatalogue.data.source.remote.response.GetMovieDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.GetTvDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.MovieResponse
import id.airham.moviecatalogue.data.source.remote.response.TvShowResponse

class RemoteRepository private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(apiService: ApiService): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository(apiService)
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<MovieResponse>> =
        NetworkHelper.call(apiService.getAllMovie())

    fun getAllTvShows(): LiveData<ApiResponse<TvShowResponse>> =
        NetworkHelper.call(apiService.getAllTvShow())

    fun getMovieDetail(id: Int): LiveData<ApiResponse<GetMovieDetailResponse>> =
        NetworkHelper.call(apiService.getMovie(id))


    fun getTvShowDetail(id: Int): LiveData<ApiResponse<GetTvDetailResponse>> =
        NetworkHelper.call(apiService.getTVShow(id))
}
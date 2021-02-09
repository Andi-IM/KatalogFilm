package id.airham.moviecatalogue.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import id.airham.moviecatalogue.utils.JsonHelper

@Suppress("DEPRECATION")
class RemoteRepository private constructor(private val jsonHelper: JsonHelper){

    // tidak disarankan menggunakan handler
    private val handler = Handler()
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository(helper)
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovieItem = MutableLiveData<ApiResponse<List<MovieItem>>>()
        handler.postDelayed({
            resultMovieItem.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovieItem
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowItem>>>{
        EspressoIdlingResource.increment()
        val resultTvShowItem = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        handler.postDelayed({
            resultTvShowItem.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShowItem
    }
}
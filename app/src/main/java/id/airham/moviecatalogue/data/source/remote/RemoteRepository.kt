package id.airham.moviecatalogue.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import id.airham.moviecatalogue.utils.network.JsonHelper

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

    // ONLINE MODE
    // NOT IN USE
    /*fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getAllMovie()
        client.enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>){
                if (response.isSuccessful){
                    val listMovies = response.body()?.results as List<MovieItem>
                    callback.onAllMoviesReceived(listMovies)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getAllTvShow()
        client.enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>){
                if (response.isSuccessful){
                    val listTvShows = response.body()?.results as List<TvShowItem>
                    callback.onAllTvShowsReceived(listTvShows)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {

            }

        })
    }*/

    // untuk tujuan pengetesan
    // all files goes offline
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
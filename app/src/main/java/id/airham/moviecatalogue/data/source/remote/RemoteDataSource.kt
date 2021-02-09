package id.airham.moviecatalogue.data.source.remote

import android.os.Handler
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import id.airham.moviecatalogue.utils.JsonHelper

@Suppress("DEPRECATION")
class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    // tidak disarankan menggunakan handler
    private val handler = Handler()
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
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
    fun getAllMoviesOffline(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShowsOffline(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvShowsReceived(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieItem>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowItem>)
    }
}
package id.airham.moviecatalogue.utils.network

import id.airham.moviecatalogue.data.source.remote.response.MovieResponse
import id.airham.moviecatalogue.data.source.remote.response.TvShowResponse
import id.airham.moviecatalogue.utils.Keys
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getAllMovie(@Query("api_key") apiKey: String = Keys.apiKey()): Call<MovieResponse>

    @GET("discover/tv")
    fun getAllTvShow(@Query("api_key") apiKey: String = Keys.apiKey()): Call<TvShowResponse>
}
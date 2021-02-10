package id.airham.moviecatalogue.data.source.remote.network

import id.airham.moviecatalogue.data.source.remote.response.GetMovieDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.GetTvDetailResponse
import id.airham.moviecatalogue.data.source.remote.response.MovieResponse
import id.airham.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie")
    fun getAllMovie(): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Call<GetMovieDetailResponse>

    @GET("discover/tv")
    fun getAllTvShow(): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTVShow(@Path("id") id: Int): Call<GetTvDetailResponse>
}
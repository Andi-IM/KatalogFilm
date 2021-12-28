package my.id.airham.core.data.source.remote.network

import my.id.airham.core.data.source.remote.response.GetMovieDetailResponse
import my.id.airham.core.data.source.remote.response.GetTvDetailResponse
import my.id.airham.core.data.source.remote.response.MovieResponse
import my.id.airham.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc")
    fun getAllMovie(): MovieResponse

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): GetMovieDetailResponse

    @GET("discover/tv?sort_by=popularity.desc")
    fun getAllTvShow(): TvShowResponse

    @GET("tv/{id}")
    fun getTVShow(@Path("id") id: Int): GetTvDetailResponse
}
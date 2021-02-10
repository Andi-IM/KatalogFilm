package id.airham.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<TvShowResultsItem>,

    @field:SerializedName("total_results")
    val totalResults: Int
) : Parcelable

@Parcelize
data class TvShowResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,


    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>? = null,


    @field:SerializedName("origin_country")
    val originCountry: List<String>? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,


    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
) : Parcelable

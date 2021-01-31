package id.airham.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    val id: String,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val firstAirDate: String,
    val voteAverage: Double,
    val type : String = "tvShow"
) : Parcelable
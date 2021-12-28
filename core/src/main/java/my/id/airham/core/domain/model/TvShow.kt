package my.id.airham.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val firstAirDate: String,
    val voteAverage: Double,
    var favorited: Boolean = false,
    var popularity: Double? = null
) : Parcelable

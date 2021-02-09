package id.airham.moviecatalogue.data.source.remote.response

data class TvShowItem(
	val id: Int,
	val originalName: String,
	val overview: String,
	val firstAirDate: String,
	val posterPath: String,
	val voteAverage: Double,
)

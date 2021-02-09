package id.airham.moviecatalogue.data.source.remote.response

data class MovieItem(
	val id: Int,
	val originalTitle: String,
	val overview: String,
	val posterPath: String,
	val releaseDate: String,
	val voteAverage: Double,
)

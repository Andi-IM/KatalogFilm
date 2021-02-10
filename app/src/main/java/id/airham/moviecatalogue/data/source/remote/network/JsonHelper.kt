package id.airham.moviecatalogue.data.source.remote.network

import android.content.Context
import id.airham.moviecatalogue.data.source.remote.response.MovieResultsItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowResultsItem
import okio.IOException
import org.json.JSONException
import org.json.JSONObject

class JsonHelper(private val context: Context) {
    private fun parsingfileToString(filename: String): String? {
        return try {
            val `is` = context.assets.open(filename)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResultsItem> {
        val list = ArrayList<MovieResultsItem>()
        try {
            val responseObject = JSONObject(parsingfileToString("movie_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (item in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(item)

                val id = movie.getInt("id")
                val originalTitle = movie.getString("original_title")
                val overview = movie.getString("overview")
                val posterPath = movie.getString("poster_path")
                val releaseDate = movie.getString("release_date")
                val voteAverage = movie.getDouble("vote_average")

                val movieResultsItem = MovieResultsItem(
                    id,
                    originalTitle,
                    overview,
                    posterPath,
                    releaseDate,
                    voteAverage
                )
                list.add(movieResultsItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShows(): List<TvShowResultsItem> {
        val list = ArrayList<TvShowResultsItem>()
        try {
            val responseObject = JSONObject(parsingfileToString("tvshow_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (item in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(item)

                val id = tvShow.getInt("id")
                val originalName = tvShow.getString("original_name")
                val overview = tvShow.getString("overview")
                val firstAirDate = tvShow.getString("first_air_date")
                val posterPath = tvShow.getString("poster_path")
                val voteAverage = tvShow.getDouble("vote_average")

                val tvShowResponse = TvShowResultsItem(
                    id,
                    originalName,
                    overview,
                    firstAirDate,
                    posterPath,
                    voteAverage
                )
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}
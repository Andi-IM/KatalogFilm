package id.airham.moviecatalogue.utils.network

import android.content.Context
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem
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
        } catch (ex: IOException){
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieItem>{
        val list = ArrayList<MovieItem>()
        try {
            val responseObject = JSONObject(parsingfileToString("movie_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (item in 0 until listArray.length()){
                val movie = listArray.getJSONObject(item)

                val id = movie.getInt("id")
                val originalTitle = movie.getString("original_title")
                val overview = movie.getString("overview")
                val posterPath = movie.getString("poster_path")
                val releaseDate = movie.getString("release_date")
                val voteAverage = movie.getDouble("vote_average")

                val movieItem = MovieItem(
                    id,
                    originalTitle,
                    overview,
                    posterPath,
                    releaseDate,
                    voteAverage)
                list.add(movieItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShows(): List<TvShowItem> {
        val list = ArrayList<TvShowItem>()
        try {
            val responseObject = JSONObject(parsingfileToString("tvshow_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (item in 0 until listArray.length()){
                val tvShow = listArray.getJSONObject(item)

                val id = tvShow.getInt("id")
                val originalName = tvShow.getString("original_name")
                val overview = tvShow.getString("overview")
                val firstAirDate = tvShow.getString("first_air_date")
                val posterPath = tvShow.getString("poster_path")
                val voteAverage = tvShow.getDouble("vote_average")

                val tvShowResponse = TvShowItem(
                    id, originalName, overview, firstAirDate, posterPath, voteAverage
                )
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }


}
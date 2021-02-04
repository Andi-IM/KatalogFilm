package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.RemoteDataSource
import id.airham.moviecatalogue.data.source.remote.response.MovieItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowItem

class FakeItemRepository(private val remoteDataSource: RemoteDataSource): ItemDataSource {

    override fun getAllMoviesOffline(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMoviesOffline(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id.toString(),
                        response.originalTitle,
                        response.overview,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return  movieResults
    }

    override fun getAllTvShowsOffline(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShowsOffline(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse){
                    val tvShow = TvShowEntity(
                        response.id.toString(),
                        response.originalName,
                        response.overview,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    fun getMovieOffline(movieId : String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getAllMoviesOffline(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieItem>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.id.toString() == movieId) {
                        movie = MovieEntity(
                            response.id.toString(),
                            response.originalTitle,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage
                        )
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    fun getTvShowOffline(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getAllTvShowsOffline(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowItem>) {
                lateinit var tvShow: TvShowEntity
                for (response in tvShowResponse) {
                    if (response.id.toString() == tvShowId){
                        tvShow = TvShowEntity(
                            response.id.toString(),
                            response.originalName,
                            response.overview,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage
                        )
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }
}
package id.airham.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.TvShowEntity

interface ItemDataSource {
    // ONLINE VERSION
    // NOT IN USE
    /*fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getAllTvShows(): LiveData<List<TvShowEntity>>*/

    // OFFLINE VERSION
    fun getAllMoviesOffline(): LiveData<List<MovieEntity>>
    fun getAllTvShowsOffline(): LiveData<List<TvShowEntity>>
}
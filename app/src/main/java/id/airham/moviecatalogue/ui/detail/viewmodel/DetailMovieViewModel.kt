package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.utils.DataDummy

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getMovie} yang akan mengembalikan data berupa
 *  Entitas Movie.
 */

class DetailMovieViewModel : ViewModel() {
    private lateinit var itemId: String
    fun setSelectedItem(itemId : String){
        this.itemId = itemId
    }

    fun getMovie() : MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateMovies()
        for (movieEntity in movieEntities){
            if (movieEntity.id == itemId){
               movie = movieEntity
            }
        }
        return movie
    }
}
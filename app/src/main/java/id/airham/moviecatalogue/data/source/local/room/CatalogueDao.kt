package id.airham.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieEntities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where favorited = 1")
    fun getFavoritedMovie(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieEntities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tvShowEntities")
    fun getTvShow(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvShowEntities where favorited = 1")
    fun getFavoritedTvShow(): LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvShowEntities WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

}
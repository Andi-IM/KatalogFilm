package my.id.airham.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import my.id.airham.core.data.source.local.entity.MovieEntity
import my.id.airham.core.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {
    // Movies
    @Query("SELECT * FROM movieEntities ORDER BY popularity DESC")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities where favorited = 1")
    fun getFavoritedMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieEntities WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    // TV SHOW
    @Query("SELECT * FROM tvShowEntities ORDER BY popularity DESC")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvShowEntities where favorited = 1")
    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tvShowEntities WHERE id = :id")
    fun getTvShowById(id: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}
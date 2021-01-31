package id.airham.moviecatalogue.ui.detail.movie

import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import id.airham.moviecatalogue.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 *  Test Case pada DetailMovieViewModels
 *  -> Memastikan data movies tidak kosong (null)
 *  -> Memastikan data yang tampilkan tidak ada satupun yang kosong (null) dan sesuai
 */

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateMovies()[0]
    private val movieId = dummyMovies.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedItem(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedItem(dummyMovies.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.id, movieEntity.id)
        assertEquals(dummyMovies.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovies.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovies.originalTitle, movieEntity.originalTitle)
        assertEquals(dummyMovies.overview, movieEntity.overview)
        assertEquals(dummyMovies.type, movieEntity.type)
        assertEquals(dummyMovies.voteAverage, movieEntity.voteAverage)
    }
}
package id.airham.moviecatalogue.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 *  Test Case pada MovieViewModel
 *  -> Memastikan data movies tidak null.
 *  -> Memastikan jumlah data movie sesuai dengan yang diharapkan.
 */

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(19, movies.size)
    }
}
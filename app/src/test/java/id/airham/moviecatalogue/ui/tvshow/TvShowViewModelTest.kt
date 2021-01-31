package id.airham.moviecatalogue.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 *  Test Case pada TvShowViewModel
 *  -> Memastikan data tvShow tidak null.
 *  -> Memastikan jumlah data tvShow sesuai dengan yang diharapkan.
 */
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getMovies() {
        val tvShow = viewModel.getTvShows()
        assertNotNull(tvShow)
        assertEquals(20, tvShow.size)
    }
}
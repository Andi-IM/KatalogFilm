package id.airham.moviecatalogue.ui.detail.tvShow

import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import id.airham.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test Case pada DetailTvViewModel
 *  -> Memastikan data tvShow tidak kosong (null)
 *  -> Memastikan data yang tampilkan tidak ada satupun yang kosong (null) dan sesuai
 */

class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTvSerie = DataDummy.generateTvs()[0]
    private val movieId = dummyTvSerie.id

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel()
        viewModel.setSelectedItem(movieId)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedItem(dummyTvSerie.id)
        val movieEntity = viewModel.getTvShow()
        assertNotNull(movieEntity)
        assertEquals(dummyTvSerie.id, movieEntity.id)
        assertEquals(dummyTvSerie.posterPath, movieEntity.posterPath)
        assertEquals(dummyTvSerie.firstAirDate, movieEntity.firstAirDate)
        assertEquals(dummyTvSerie.originalName, movieEntity.originalName)
        assertEquals(dummyTvSerie.overview, movieEntity.overview)
        assertEquals(dummyTvSerie.type, movieEntity.type)
        assertEquals(dummyTvSerie.voteAverage, movieEntity.voteAverage,0.0)
    }
}
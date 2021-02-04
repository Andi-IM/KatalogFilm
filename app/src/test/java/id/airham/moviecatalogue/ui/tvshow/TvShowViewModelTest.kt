package id.airham.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.data.source.ItemRepository
import id.airham.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 *  Test Case pada TvShowViewModel
 *  -> Memastikan data tvShow tidak null.
 *  -> Memastikan jumlah data tvShow sesuai dengan yang diharapkan.
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemRepository: ItemRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(itemRepository)
    }

    @Test
    fun getMovies() {
        val dummyTvShows = DataDummy.generateTvs()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(itemRepository.getAllTvShowsOffline()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShows().value
        verify(itemRepository).getAllTvShowsOffline()
        assertNotNull(tvShowEntity)
        assertEquals(20, tvShowEntity?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}
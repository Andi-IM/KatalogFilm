package id.airham.moviecatalogue.ui.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.data.source.ItemRepository
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import id.airham.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test Case pada DetailTvViewModel
 *  -> Memastikan data tvShow tidak kosong (null)
 *  -> Memastikan data yang tampilkan tidak ada satupun yang kosong (null) dan sesuai
 */

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTvSerie = DataDummy.generateTvs()[0]
    private val tvShowId = dummyTvSerie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @Mock
    private lateinit var itemRepository: ItemRepository
    
    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>
    
    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(itemRepository)
        viewModel.setSelectedItem(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvSerie
        
        `when`(itemRepository.getTvShowOffline(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(itemRepository).getTvShowOffline(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvSerie.id, tvShowEntity.id)
        assertEquals(dummyTvSerie.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvSerie.firstAirDate, tvShowEntity.firstAirDate)
        assertEquals(dummyTvSerie.originalName, tvShowEntity.originalName)
        assertEquals(dummyTvSerie.overview, tvShowEntity.overview)
        assertEquals(dummyTvSerie.type, tvShowEntity.type)
        assertEquals(dummyTvSerie.voteAverage, tvShowEntity.voteAverage,0.1)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvSerie)
    }
}
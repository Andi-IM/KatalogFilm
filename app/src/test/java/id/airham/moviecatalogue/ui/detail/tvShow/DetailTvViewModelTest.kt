package id.airham.moviecatalogue.ui.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import id.airham.moviecatalogue.utils.DataDummy
import id.airham.moviecatalogue.vo.Resource
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
    private lateinit var catalogueRepository: CatalogueRepository
    
    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>
    
    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(catalogueRepository)
        viewModel.setSelectedItem(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShowResource = Resource.success(dummyTvSerie)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = tvShowResource
        
        `when`(catalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowResource)
    }
}
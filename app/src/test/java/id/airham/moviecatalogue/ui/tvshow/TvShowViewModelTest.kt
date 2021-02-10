package id.airham.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.vo.Resource
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
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(20)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(catalogueRepository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShows().value?.data
        verify(catalogueRepository).getAllTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(20, tvShowEntity?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}
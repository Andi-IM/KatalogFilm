package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvViewModelTest {

    private lateinit var viewModel: FavTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavTvViewModel(catalogueRepository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShows = pagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(20)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(catalogueRepository.getFavoritedTvShows()).thenReturn(tvShows)
        val movieEntities = viewModel.getFavorites().value
        verify(catalogueRepository).getFavoritedTvShows()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(20, movieEntities?.size)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}
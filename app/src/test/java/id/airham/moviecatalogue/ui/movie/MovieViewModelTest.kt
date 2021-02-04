package id.airham.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.MovieEntity
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
 *  Test Case pada MovieViewModel
 *  -> Memastikan data movies tidak null.
 *  -> Memastikan jumlah data movie sesuai dengan yang diharapkan.
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemRepository: ItemRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(itemRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(itemRepository.getAllMoviesOffline()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(itemRepository).getAllMoviesOffline()
        assertNotNull(movieEntities)
        assertEquals(19, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}
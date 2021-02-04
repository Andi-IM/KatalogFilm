package id.airham.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.source.ItemRepository
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
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
 *  Test Case pada DetailMovieViewModels
 *  -> Memastikan data movies tidak kosong (null)
 *  -> Memastikan data yang tampilkan tidak ada satupun yang kosong (null) dan sesuai
 */

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateMovies()[0]
    private val movieId = dummyMovies.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemRepository: ItemRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(itemRepository)
        viewModel.setSelectedItem(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovies

        `when`(itemRepository.getMovieOffline(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(itemRepository).getMovieOffline(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.id, movieEntity.id)
        assertEquals(dummyMovies.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovies.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovies.originalTitle, movieEntity.originalTitle)
        assertEquals(dummyMovies.overview, movieEntity.overview)
        assertEquals(dummyMovies.type, movieEntity.type)
        assertEquals(dummyMovies.voteAverage, movieEntity.voteAverage, 0.1)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }
}
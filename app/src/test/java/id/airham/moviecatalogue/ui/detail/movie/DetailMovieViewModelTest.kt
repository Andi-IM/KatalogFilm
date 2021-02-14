package id.airham.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.CatalogueRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import id.airham.moviecatalogue.utils.DataDummy
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
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedItem(movieId)
    }

    @Test
    fun getMovie() {
        val movieResource = Resource.success(dummyMovies)
        val movieLiveData = MutableLiveData<Resource<MovieEntity>>()
        movieLiveData.value = movieResource

        `when`(catalogueRepository.getMovie(movieId)).thenReturn(movieLiveData)

        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movieResource)

        val movie = viewModel.movie.value?.data!!
        assertNotNull(movie)

        assertEquals(dummyMovies.id, movie.id)
        assertEquals(dummyMovies.originalTitle, movie.originalTitle)
        assertEquals(dummyMovies.overview, movie.overview)
        assertEquals(dummyMovies.posterPath, movie.posterPath)
        assertEquals(dummyMovies.releaseDate, movie.releaseDate)
        assertEquals(dummyMovies.voteAverage, movie.voteAverage, 0.01)
        assertEquals(dummyMovies.favorited, movie.favorited)
    }

    @Test
    fun setMovieFavorited() {
        val movieResource = Resource.success(dummyMovies)
        val movieLiveData = MutableLiveData<Resource<MovieEntity>>()
        movieLiveData.value = movieResource

        `when`(catalogueRepository.getMovie(movieId)).thenReturn(movieLiveData)
        viewModel.movie.observeForever(movieObserver)
        viewModel.setFavorite()
        verify(movieObserver).onChanged(movieResource)

        val movie = movieLiveData.value?.data!!
        catalogueRepository.setMovieFavorite(movie, !movie.favorited)

        assertEquals(dummyMovies.favorited, movie.favorited)
    }
}
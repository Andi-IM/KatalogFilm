package id.airham.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.remote.RemoteDataSource
import id.airham.moviecatalogue.utils.DataDummy
import id.airham.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class ItemRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val itemRepository = FakeItemRepository(remote)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponses = DataDummy.generateRemoteDummyTvs()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMoviesOffline(any())
        val movieEntities = LiveDataTestUtil.getValue(itemRepository.getAllMoviesOffline())
        verify(remote).getAllMoviesOffline(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShowsOffline(any())
        val tvShowEntities = LiveDataTestUtil.getValue(itemRepository.getAllTvShowsOffline())
        verify(remote).getAllTvShowsOffline(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }
}
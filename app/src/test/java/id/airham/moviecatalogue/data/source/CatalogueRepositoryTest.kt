package id.airham.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.utils.AppExecutors
import id.airham.moviecatalogue.utils.DataDummy
import id.airham.moviecatalogue.utils.PagedListUtil.mockPagedList
import id.airham.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class CatalogueRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val local = mock(LocalRepository::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteMovies()
    private val tvShowResponses = DataDummy.generateRemoteTvs()

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllMovies()

        val movieEntities = Resource.success(mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllTvShows()

        val tvShowEntities = Resource.success(mockPagedList(DataDummy.generateTvs()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}
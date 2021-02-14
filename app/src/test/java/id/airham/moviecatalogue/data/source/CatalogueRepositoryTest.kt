package id.airham.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.airham.moviecatalogue.data.source.local.LocalRepository
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.util.PagedListUtil.mockPagedList
import id.airham.moviecatalogue.utils.AppExecutors
import id.airham.moviecatalogue.utils.DataDummy
import id.airham.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 *  ## Test Case in Catalogue Repo Test
 * - Mendapatkan Data Movie : <br />
 *   -- Memastikan Movie tidak kosong (null)
 *   -- Memastikan Data Local yang didapat = Data Remote
 *
 * - Mendapatkan Data TvShow
 *   -- Memastikan TvShow tidak kosong (null)
 *   -- Memastikan Data local yang didapat = Data Remote
 *
 * - Mendapatkan Data Movie yang difavoritkan
 *   -- Memastikan Favorite Movie tidak kosong (null)
 *
 * - Mendapatkan Data Tv yang difavoritkan
 *   -- Memastikan Favorite Tv Show tidak kosong (null)
 *
 */

@Suppress("UNCHECKED_CAST")
class CatalogueRepositoryTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val local = mock(LocalRepository::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateMovies()
    private val sampleMovie = movieResponses[0]

    private val tvShowResponses = DataDummy.generateTvs()
    private val sampleTvShow = tvShowResponses[0]

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

    @Test
    fun setFavoriteMovie() {
        local.setMovieFavorite(sampleMovie, true)

        verify(local).setMovieFavorite(sampleMovie, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoritedMovies()

        val movieEntities = Resource.success(mockPagedList(DataDummy.generateMovies()))

        verify(local).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteTvShow() {
        local.setTvShowFavorite(sampleTvShow, true)

        verify(local).setTvShowFavorite(sampleTvShow, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoritedTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoritedTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoritedTvShows()

        val tvShowEntities = Resource.success(mockPagedList(DataDummy.generateTvs()))
        verify(local).getFavoritedTvShows()
        assertNotNull(tvShowEntities.data)
    }


}
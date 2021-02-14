package id.airham.moviecatalogue.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.remote.RemoteRepository
import id.airham.moviecatalogue.data.source.remote.network.ApiConfig
import id.airham.moviecatalogue.ui.home.CustomAssertions.Companion.hasItemCount
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


/**
 * Test Case HomeActivityTest
 * -> Membuka Movie Navigation
 *      -> melakukan aksi klik pada navigation_movie
 *      -> memastikan navigation_movie tampil
 * -> Menampilkan Movies
 *      -> memberi aksi klik pada menu navigasi "Movies"
 *      -> Memastikan rv_movie dalam keadaan tampil
 *      -> Gulir rv_movie ke posisi data terakhir
 * -> Menampilkan data Movies
 *      -> memberi aksi klik pada menu navigasi "Movies"
 *      -> memberi aksi klik pada data pertama rv_movie
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk sipnosis tampil sesuai dengan data yang diharapkan
 * -> Membuka Tv Show Navigation
 *      -> melakukan aksi klik pada navigation_tv_show
 *      -> memastikan navigation_tv_show_tampil
 * -> Menampilkan TvShow
 *      -> memberi aksi klik pada menu navigasi "TV Show"
 *      -> Memastikan rv_tv_show dalam keadaan tampil
 *      -> Gulir rv_tv_show ke posisi data terakhir
 * -> Menampilkan data TvShow
 *      -> memberi aksi klik pada menu navigasi "TV Show"
 *      -> memberi aksi klik pada data pertama rv_tv_show
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk sipnosis tampil sesuai dengan data yang diharapkan
 * -> Memberi aksi favorite pada salah satu Movie
 *      -> memberi aksi klik pada menu navigasi "Movies"
 *      -> memberi aksi klik pada data pertama rv_movies
 *      -> memberi aksi klik pada menu action_favorite
 *      -> memastikan state menu action_favorite berubah
 *      -> memberi aksi "back" kembali ke halaman awal (default : favorite)
 *      -> memastikan item pada rv_fav_movie tampil
 *      -> memberi aksi klik data pertama rv_fav_movie
 *      -> memberi aksi klik pada action_favorite
 *      -> memastikan state menu action_favorite berubah
 *      -> memberi aksi "back" kembali ke halaman awal (default : favorite)
 *      -> memastikan item pada rv_fav_movie telah hilang
 * -> memberi aksi favorite pada salah satu TvShow
 *      -> memberi aksi klik pada menu navigasi "TvShow"
 *      -> memberi aksi klik pada data pertama rv_tv_show
 *      -> memberi aksi klik pada menu action_favorite
 *      -> memastikan state menu action_favorite berubah
 *      -> memberi aksi "back" kembali ke halaman awal (default : favorite)
 *      -> memberi aksi swipe ke kanan (fav_tv_fragment)
 *      -> memastikan item pada rv_fav_tv_show tampil
 *      -> memberi aksi klik data pertama rv_fav_movie
 *      -> memberi aksi klik pada action_favorite
 *      -> memastikan state menu action_favorite berubah
 *      -> memberi aksi "back" kembali ke halaman awal (default : favorite)
 *      -> memberi aksi swipe ke kanan (fav_tv_fragment)
 *      -> memastikan item pada rv_fav_tv_show telah hilang
 * -> Menandai Movie sebagai Favorit
 *      -> melakukan aksi klik pada navigation_movie
 *      -> melakukan aksi klik pada item pertama dari daftar
 *      -> memastikan action_favorite muncul
 *      -> melakukan aksi klik pada action_favorite
 *      -> memastikan action_favorite berubah state
 *      -> melakukan aksi kembali ke view sebelumnya
 *      -> melakukan aksi klik pada navigation_favorite
 *      -> memastikan navigation_favorite tampil
 *      -> memastikan item pada rv_fav_movie tampil
 * -> Menandai TvShow sebagai Favorit
 *      -> melakukan aksi klik pada navigation_tv_show
 *      -> melakukan aksi klik pada item pertama dari daftar
 *      -> memastikan action_favorite muncul
 *      -> melakukan aksi klik pada action_favorite
 *      -> memastikan action_favorite berubah state
 *      -> melakukan aksi kembali ke view sebelumnya
 *      -> melakukan aksi klik pada navigation_favorite
 *      -> memastikan navigation_favorite tampil
 *      -> melakukan aksi swipe ke kiri
 *      -> memastikan item pada rv_fav_movie tampil
 *
 */

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {
    private val apiConfig = ApiConfig.getApiService()
    private val movieRemote = RemoteRepository(apiConfig)
    private val tvShowRemote = RemoteRepository(apiConfig)

    private val sampleMovie = movieRemote.getAllMovies()
    private val sampleTvShow = tvShowRemote.getAllTvShows()

    private lateinit var instrumentalContext: Context

    companion object {
        private const val FIRST_ITEM = 0
    }

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun test1() { // openMovieNavigation
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun test2() { // loadMovies
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        val movieSize = sampleMovie.value?.body?.results?.size!!
        onView(withId(R.id.rv_movie)).perform(scrollToPosition<RecyclerView.ViewHolder>(movieSize))
    }

    @Test
    fun test3() { // loadDetailMovie
        onView(withId(R.id.navigation_movie)).perform(click())
        val results = sampleMovie.value?.body?.results!!
        val firstMovie = results.first()
        val position = results.indexOf(firstMovie)

        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position, click()
            )
        )

        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(firstMovie.originalTitle)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(firstMovie.releaseDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(firstMovie.overview)))
    }

    @Test
    fun test4() { // openTvShowNavigation
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
    }

    @Test
    fun test5() { // loadTvShows
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        val tvShowSize = sampleTvShow.value?.body?.results?.size!!
        onView(withId(R.id.rv_tv_show))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(tvShowSize))
    }

    @Test
    fun test6() { // loadDetailTvShow
        onView(withId(R.id.navigation_tv_show)).perform(click())
        val results = sampleTvShow.value?.body?.results!!
        val firstTvShow = results.first()
        val position = results.indexOf(firstTvShow)

        onView(withId(R.id.rv_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position, click()
            )
        )
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(firstTvShow.originalName)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(firstTvShow.firstAirDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(firstTvShow.overview)))
    }

    @Test
    fun test7() { // openFavoriteNavigation
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.navigation_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test8() { // onMovieSetFavorite
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.navigation_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).check(hasItemCount(1))
        onView(withId(R.id.rv_fav_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_fav_movie)).check(hasItemCount(1))
    }

    @Test
    fun test9() { // onTvShowSetFavorite
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.view_pager)).perform(swipeLeft())

        delay150milis()
        onView(withId(R.id.rv_fav_tv_show)).check(hasItemCount(1))
        onView(withId(R.id.rv_fav_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_fav_tv_show)).check(hasItemCount(1)) // item exist, but no data
    }

    private fun delay150milis() {
        try {
            Thread.sleep(150)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
package id.airham.moviecatalogue.ui.home

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
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.utils.DataDummy
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test Case HomeActivityTest
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
 * -> Menampikan TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
 *      -> memberi aksi klik pada menu navigasi "TV Show"
 *      -> Memastikan rv_tv_show dalam keadaan tampil
 *      -> Gulir rv_tv_show ke posisi data terakhir
 * -> Menampilkan data TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
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
 */

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateMovies()
    private val sampleMovie = dummyMovie[0]
    private val dummyTvShow = DataDummy.generateTvs()
    private val sampleTvShow = dummyTvShow[0]

    companion object {
        private const val FIRST_ITEM = 0
    }

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(sampleMovie.originalTitle)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(sampleMovie.releaseDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(sampleMovie.overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(sampleTvShow.originalName)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(sampleTvShow.firstAirDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(sampleTvShow.overview)))
    }

    @Test
    fun movieFavoriteTest() {
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun tvShowFavoriteTest() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_fav_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_show)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST_ITEM,
                click()
            )
        )
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_fav_tv_show)).check(matches(isDisplayed()))
    }
}
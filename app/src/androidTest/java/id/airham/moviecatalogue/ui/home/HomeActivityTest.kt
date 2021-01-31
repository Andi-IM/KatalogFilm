package id.airham.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test Case HomeActivityTest
 * -> Menampilkan Movies
 *      -> Memastikan rv_movie dalam keadaan tampil
 *      -> Gulir rv_movie ke posisi data terakhir
 * -> Menampilkan data Movies
 *      -> memberi aksi klik pada data pertama rv_movie
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 * -> Menampikan TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
 *      -> Memastikan rv_tv_show dalam keadaan tampil
 *      -> Gulir rv_tv_show ke posisi data terakhir
 * -> Menampilkan data TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
 *      -> memberi aksi klik pada data pertama rv_tv_show
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    private val dummyMovie = DataDummy.generateMovies()
    private val dummyTvShow = DataDummy.generateTvs()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(dummyMovie[0].originalTitle)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyMovie[0].releaseDate)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(dummyTvShow[0].originalName)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyTvShow[0].firstAirDate)))
    }
}
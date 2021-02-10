package id.airham.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
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
 *      -> Memastikan rv_movie dalam keadaan tampil
 *      -> Gulir rv_movie ke posisi data terakhir
 * -> Menampilkan data Movies
 *      -> memberi aksi klik pada data pertama rv_movie
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk sipnosis tampil sesuai dengan data yang diharapkan
 * -> Menampikan TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
 *      -> Memastikan rv_tv_show dalam keadaan tampil
 *      -> Gulir rv_tv_show ke posisi data terakhir
 * -> Menampilkan data TvShow
 *      -> memberi aksi klik pada tab "TV SHOW"
 *      -> memberi aksi klik pada data pertama rv_tv_show
 *      -> memastikan textView untuk name tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk date tampil sesuai dengan data yang diharapkan
 *      -> memastikan textView untuk sipnosis tampil sesuai dengan data yang diharapkan
 */

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateMovies()
    private val dummyTvShow = DataDummy.generateTvs()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown(){
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
        onView(withId(R.id.rv_movie)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(dummyMovie[0].originalTitle)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(dummyMovie[0].overview)))
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
        onView(withId(R.id.rv_tv_show)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(withText(dummyTvShow[0].originalName)))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(withText(dummyTvShow[0].firstAirDate)))
        onView(withId(R.id.sinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.sinopsis)).check(matches(withText(dummyTvShow[0].overview)))
    }

    @Test
    fun onMovieSetBookmark(){
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.action_favorite)).check(matches(isEnabled()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun onTvShowSetBookmark(){

    }
}
package id.airham.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.ui.favorite.tabs.movie.FavMovieFragment
import id.airham.moviecatalogue.ui.favorite.tabs.tvshow.FavTvFragment

/**
 *  Kelas ini merupakan adapter untuk viewPager
 *  bertujuan untuk membuat item dari viewPager beserta titel dari ViewPager.
 *  view pager ini diisi dengan MovieFragment dan TvShowFragment
 */

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}
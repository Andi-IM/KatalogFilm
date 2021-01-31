package id.airham.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.databinding.ActivityHomeBinding

/**
 *  kelas ini merupakan halaman utama dari aplikasi
 *  kelas ini mengandung tablayout yang berisi 2 fragment, MovieFragment dan TvShowFragment
 *  semua kelas pada project ini mengadaptasi fitur viewBinding
 *
 *  n/b : project ini sudah pernah dibuat ulang sebanyak 3 kali
 *  kendala : testing dan infinityloop saat run apl
 */

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionPagerAdapter= SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionPagerAdapter
        activityHomeBinding.tabLayout.setupWithViewPager(activityHomeBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}
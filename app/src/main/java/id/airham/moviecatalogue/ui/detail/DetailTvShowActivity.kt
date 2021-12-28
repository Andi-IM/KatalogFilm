package id.airham.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.databinding.ActivityDetailTvShowBinding
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import my.id.airham.core.domain.model.TvShow

/**
 *  Kelas ini merupakan activity yang mendapatkan data dari item tvShow
 *  data yang dikeluarkan (setidaknya) lebih rinci daripada item tvShow
 *
 *  semua data dikirimkan melalui putExtra
 */
@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var _activityDetailTvShowBinding: ActivityDetailTvShowBinding? = null
    private val mainBinding get() = _activityDetailTvShowBinding
    private val contentBinding get() = _activityDetailTvShowBinding?.detailItem

    private val tvViewModel: DetailTvViewModel by viewModels()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        setSupportActionBar(mainBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailTvShow = intent.getParcelableExtra<TvShow>(EXTRA_DATA)
        tvViewModel.setSelectedItem(detailTvShow?.id!!)
        showTv(detailTvShow)
    }

    private fun showTv(detailTvShow: TvShow?) {
        detailTvShow.let {
            contentBinding?.name?.text = detailTvShow?.originalName
            contentBinding?.sinopsis?.text = detailTvShow?.overview
            contentBinding?.date?.text = detailTvShow?.firstAirDate
            contentBinding?.poster?.let {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w342/${detailTvShow?.posterPath}")
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                            .override(132, 198)
                    )
                    .into(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        tvViewModel.tvShow.observe(this, { tvShow ->
            if (tvShow != null) {
                setFavoriteState(tvShow.data?.favorited!!)
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            tvViewModel.setFavoriteTvShow()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white)
        }
    }
}
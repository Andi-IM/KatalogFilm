package id.airham.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.databinding.ActivityDetailTvShowBinding
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.viewmodel.ViewModelFactory
import id.airham.moviecatalogue.vo.Status

/**
 *  Kelas ini merupakan activity yang mendapatkan data dari item tvShow
 *  data yang dikeluarkan (setidaknya) lebih rinci daripada item tvShow
 *
 *  semua data dikirimkan melalui putExtra
 */
@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
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

        val factory = ViewModelFactory.getInstance(this)
        tvViewModel = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_ID)
            tvViewModel.setSelectedItem(id)

            tvViewModel.tvShow.observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> mainBinding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> if (tvShow.data != null) {
                            mainBinding?.progressBar?.visibility = View.GONE
                            mainBinding?.content?.visibility = View.VISIBLE
                            showTv(tvShow.data)
                        }
                        Status.ERROR -> {
                            mainBinding?.progressBar?.visibility = View.GONE
                            showToast(this, "Something Error")
                        }
                    }
                }
            })
        }
    }

    private fun showTv(tvShowEntity: TvShowEntity) {
        contentBinding?.name?.text = tvShowEntity.originalName
        contentBinding?.sinopsis?.text = tvShowEntity.overview
        contentBinding?.date?.text = tvShowEntity.firstAirDate
        contentBinding?.poster?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342/${tvShowEntity.posterPath}")
                .apply(
                    RequestOptions
                        .placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .override(132, 198)
                )
                .into(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        tvViewModel.tvShow.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> mainBinding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> if (tvShow.data != null) {
                        mainBinding?.progressBar?.visibility = View.GONE
                        val state = tvShow.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        mainBinding?.progressBar?.visibility = View.GONE
                        showToast(this, "Something Error")
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            tvViewModel.setFavorite()
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
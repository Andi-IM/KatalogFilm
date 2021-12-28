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
import id.airham.moviecatalogue.databinding.ActivityDetailMovieBinding
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import my.id.airham.core.domain.model.Movie

/**
 *  Kelas ini merupakan activity yang mendapatkan data dari item movie
 *  data yang dikeluarkan (setidaknya) lebih rinci daripada item movie
 *
 *  semua data dikirimkan melalui putExtra
 */
@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_id"
    }

    private var _activityDetailContentBinding: ActivityDetailMovieBinding? = null
    private val mainBinding get() = _activityDetailContentBinding
    private val contentBinding get() = _activityDetailContentBinding?.detailItem

    private val movieViewModel: DetailMovieViewModel by viewModels()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailContentBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        setSupportActionBar(mainBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        movieViewModel.setSelectedItem(detailMovie?.id!!)
        showMovie(detailMovie)
    }

    private fun showMovie(movieEntity: Movie) {
        contentBinding?.name?.text = movieEntity.originalTitle
        contentBinding?.sinopsis?.text = movieEntity.overview
        contentBinding?.date?.text = movieEntity.releaseDate
        contentBinding?.poster?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342/${movieEntity.posterPath}")
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        movieViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                setFavoriteState(movie.data?.favorited!!)
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            movieViewModel.setFavoriteMovie()
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
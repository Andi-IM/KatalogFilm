package id.airham.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.databinding.ActivityDetailMovieBinding
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import id.airham.moviecatalogue.viewmodel.ViewModelFactory
import id.airham.moviecatalogue.vo.Status

/**
 *  Kelas ini merupakan activity yang mendapatkan data dari item movie atau tvShow
 *  data yang dikeluarkan (setidaknya) lebih rinci daripada item movie atau tvShow
 *
 *  semua data dikirimkan melalui putExtra
 *
 *  sebelum melakukan draw data, data yang masuk dicek terlebih dahulu tipenya apa dengan logika
 *  boolean. jika tipenya berupa 'movie' maka showMovie(), jika tipenya berupa 'tvShow' maka showTv()
 */

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private var _activityDetailContentBinding: ActivityDetailMovieBinding? = null

    private val mainBinding get() = _activityDetailContentBinding
    private val contentBinding get() = _activityDetailContentBinding?.detailItem

    private lateinit var movieViewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailContentBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        setSupportActionBar(mainBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_ID)

            movieViewModel.setSelectedItem(id)

            movieViewModel.getMovie().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> mainBinding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> if (movie.data != null) {
                            mainBinding?.progressBar?.visibility = View.GONE
                            mainBinding?.content?.visibility = View.VISIBLE
                            showMovie(movie.data)
                        }
                        Status.ERROR -> {
                            mainBinding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Something Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })


        }
    }

    private fun showMovie(movieEntity: MovieEntity) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        movieViewModel.getMovie().observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> mainBinding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        mainBinding?.progressBar?.visibility = View.GONE
                        val state = movie.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        mainBinding?.progressBar?.visibility = View.GONE
                        Toast.makeText(applicationContext, "Something Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            movieViewModel.setFavorite()
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
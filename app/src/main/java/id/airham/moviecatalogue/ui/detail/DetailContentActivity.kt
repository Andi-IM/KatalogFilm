package id.airham.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.databinding.ActivityDetailContentBinding
import id.airham.moviecatalogue.databinding.ContentDetailContentBinding
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel

/**
 *  Kelas ini merupakan activity yang mendapatkan data dari item movie atau tvShow
 *  data yang dikeluarkan (setidaknya) lebih rinci daripada item movie atau tvShow
 *
 *  semua data dikirimkan melalui putExtra
 *
 *  sebelum melakukan draw data, data yang masuk dicek terlebih dahulu tipenya apa dengan logika
 *  boolean. jika tipenya berupa 'movie' maka showMovie(), jika tipenya berupa 'tvShow' maka showTv()
 */

class DetailContentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailContentBinding: ContentDetailContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailContentBinding = ActivityDetailContentBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailContentBinding.detailItem
        setContentView(activityDetailContentBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]
        val tvViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTvViewModel::class.java]

        val isMovie = "movie"
        val isTvShow = "tvShow"

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID).toString()
            val type = extras.getString(EXTRA_TYPE)

            if (type == isMovie) {
                movieViewModel.setSelectedItem(id)
                val movie = movieViewModel.getMovie()
                supportActionBar?.title = movie.originalTitle
                showMovie(movie)
            }

            if (type == isTvShow){
                tvViewModel.setSelectedItem(id)
                val tvShow = tvViewModel.getTvShow()
                supportActionBar?.title = tvShow.originalName
                showTv(tvShow)
            }
        }
    }

    private fun showMovie(movieEntity: MovieEntity) {
        detailContentBinding.name.text = movieEntity.originalTitle
        detailContentBinding.sinopsis.text = movieEntity.overview
        detailContentBinding.date.text = movieEntity.releaseDate
        Glide.with(this)
            .load(movieEntity.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.poster)
    }

    private fun showTv(tvShowEntity: TvShowEntity) {
        detailContentBinding.name.text = tvShowEntity.originalName
        detailContentBinding.sinopsis.text = tvShowEntity.overview
        detailContentBinding.date.text = tvShowEntity.firstAirDate
        Glide.with(this)
            .load(tvShowEntity.posterPath)
            .apply(RequestOptions
                .placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .override(132,198))
            .into(detailContentBinding.poster)
    }
}
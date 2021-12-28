package id.airham.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.databinding.ItemMoviesBinding
import my.id.airham.core.domain.model.Movie

/**
 *  Kelas ini merupakan adapter untuk recyclerview yang ditampilkan pada MovieFragment.
 *  Item yang ditampilkan diambil dari item_movie berupa ringkasan dari film.
 *
 *  Sebelum ditampilkan, listMovies dikosongkan terlebih dahulu sebelum diisi oleh data
 *  dari MovieEntity.
 *
 *  Item yang dapat diklik berasal dari itemview.setOnClickListener, sehingga item dapat melakukan
 *  intent dari HomeActivity (induknya) menuju DetailContentActivty.
 */

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    var clickListener: ItemOnClickListener? = null

    interface ItemOnClickListener {
        fun onclick(id: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMoviesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it, clickListener) }
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, onClick: ItemOnClickListener?) {
            with(binding) {
                movieName.text = movie.originalTitle
                val getStarRating = 5 * (movie.voteAverage.toFloat() / 10)
                movieRating.rating = getStarRating
                movieReleaseDate.text =
                    itemView.resources.getString(R.string.release_date, movie.releaseDate)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342/${movie.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(movieImage)

                if (onClick != null) {
                    itemView.setOnClickListener {
                        onClick.onclick(movie.id)
                    }
                }

            }
        }
    }
}


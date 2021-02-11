package id.airham.moviecatalogue.ui.favorite.tabs.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.databinding.ItemMoviesBinding

class FavMovieAdapter :
    PagedListAdapter<MovieEntity, FavMovieAdapter.FavViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    var clickListener: ItemOnClickListener? = null

    interface ItemOnClickListener {
        fun onclick(id: Int)
    }

    class FavViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity, onClick: ItemOnClickListener?) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder =
        FavViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, clickListener)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)
}
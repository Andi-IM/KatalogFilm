package id.airham.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.databinding.ItemMoviesBinding

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

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()
    var clickListener: ItemOnClickListener? = null

    interface ItemOnClickListener {
        fun onclick(type: String, id: Int)
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position], clickListener)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
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
                        onClick.onclick(movie.type, movie.id)
                    }
                }

            }
        }
    }
}


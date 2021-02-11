package id.airham.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.databinding.ItemTvShowsBinding

/**
 *  Kelas ini merupakan adapter untuk recyclerview yang ditampilkan pada TvShowFragment.
 *  Item yang ditampilkan diambil dari item_tv_show berupa ringkasan dari siaran Tv.
 *
 *  Sebelum ditampilkan, listMovies dikosongkan terlebih dahulu sebelum diisi oleh data
 *  dari TvShowEntity.
 *
 *  Item yang dapat diklik berasal dari itemview.setOnClickListener, sehingga item dapat melakukan
 *  intent dari HomeActivity (induknya) menuju DetailContentActivty.
 */

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
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
    ): TvShowAdapter.TvShowViewHolder {
        return TvShowViewHolder(
            ItemTvShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it, clickListener) }
    }

    inner class TvShowViewHolder(private val binding: ItemTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity, onClick: ItemOnClickListener?) {
            with(binding) {
                tvName.text = tvShow.originalName
                val getStarRating = 5 * (tvShow.voteAverage.toFloat() / 10)
                tvRating.rating = getStarRating
                tvAiringDate.text =
                    itemView.context.resources.getString(R.string.airing_date, tvShow.firstAirDate)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342/${tvShow.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(tvImage)

                if (onClick != null) {
                    itemView.setOnClickListener {
                        onClick.onclick(tvShow.id)
                    }
                }

            }
        }
    }
}

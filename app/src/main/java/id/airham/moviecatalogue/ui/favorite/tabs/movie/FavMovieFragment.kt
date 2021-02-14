package id.airham.moviecatalogue.ui.favorite.tabs.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import id.airham.moviecatalogue.R
import id.airham.moviecatalogue.databinding.FavMovieFragmentBinding
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private var _favMovieFragmentBinding: FavMovieFragmentBinding? = null
    private val binding get() = _favMovieFragmentBinding

    private lateinit var viewModel: FavMovieViewModel
    private lateinit var adapter: FavMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favMovieFragmentBinding = FavMovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onItemTouchHelper.attachToRecyclerView(binding?.rvFavMovie)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

            adapter = FavMovieAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorites().observe(viewLifecycleOwner, { movies ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(movies)
                adapter.clickListener = object : FavMovieAdapter.ItemOnClickListener {
                    override fun onclick(id: Int) {
                        val intent =
                            Intent(context, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_ID, id)
                        startActivity(intent)
                    }
                }
                adapter.notifyDataSetChanged()
            })

            binding?.rvFavMovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavMovie?.setHasFixedSize(true)
            binding?.rvFavMovie?.adapter = adapter
        }
    }

    private val onItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _favMovieFragmentBinding = null
    }

}
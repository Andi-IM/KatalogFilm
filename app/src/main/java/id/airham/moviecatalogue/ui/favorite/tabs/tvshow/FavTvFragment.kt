package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

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
import id.airham.moviecatalogue.databinding.FavTvFragmentBinding
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

class FavTvFragment : Fragment() {

    private var _favTvFragmentBinding: FavTvFragmentBinding? = null
    private val binding get() = _favTvFragmentBinding

    private lateinit var viewModel: FavTvViewModel
    private lateinit var adapter: FavTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favTvFragmentBinding = FavTvFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onItemTouchHelper.attachToRecyclerView(binding?.rvFavTvShow)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

            val adapter = FavTvShowAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorites().observe(viewLifecycleOwner, { tvShows ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(tvShows)

                adapter.notifyDataSetChanged()
            })

            binding?.rvFavTvShow?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavTvShow?.setHasFixedSize(true)
            binding?.rvFavTvShow?.adapter = adapter
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
        _favTvFragmentBinding = null
    }

}
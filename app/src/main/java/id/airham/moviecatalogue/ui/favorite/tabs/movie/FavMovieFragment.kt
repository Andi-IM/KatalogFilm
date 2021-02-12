package id.airham.moviecatalogue.ui.favorite.tabs.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.airham.moviecatalogue.databinding.FavMovieFragmentBinding
import id.airham.moviecatalogue.ui.movie.MovieAdapter

@AndroidEntryPoint
class FavMovieFragment : Fragment() {

    private var _favMovieFragmentBinding: FavMovieFragmentBinding? = null
    private val binding get() = _favMovieFragmentBinding

    private val viewModel: FavMovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favMovieFragmentBinding = FavMovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val adapter = MovieAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorites().observe(viewLifecycleOwner, { movies ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(movies)
                adapter.notifyDataSetChanged()
            })

            binding?.rvFavMovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavMovie?.setHasFixedSize(true)
            binding?.rvFavMovie?.adapter = adapter
        }
    }

}
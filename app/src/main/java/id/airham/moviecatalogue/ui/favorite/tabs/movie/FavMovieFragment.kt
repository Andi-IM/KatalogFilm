package id.airham.moviecatalogue.ui.favorite.tabs.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FavMovieFragmentBinding
import id.airham.moviecatalogue.ui.movie.MovieAdapter
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private var _favMovieFragmentBinding: FavMovieFragmentBinding? = null
    private val binding get() = _favMovieFragmentBinding

    private lateinit var viewModel: FavMovieViewModel
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
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

            val adapter = MovieAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorites().observe(viewLifecycleOwner, {movies ->
                binding?.progressBar?.visibility = View.GONE
                adapter.setMovies(movies)
                adapter.notifyDataSetChanged()
            })

            binding?.rvFavMovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavMovie?.setHasFixedSize(true)
            binding?.rvFavMovie?.adapter = adapter
        }
    }

}
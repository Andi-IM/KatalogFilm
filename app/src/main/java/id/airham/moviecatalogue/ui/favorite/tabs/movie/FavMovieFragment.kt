package id.airham.moviecatalogue.ui.favorite.tabs.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FavMovieFragmentBinding
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
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
            viewModel.getFavorites().observe(viewLifecycleOwner, { movies ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(movies)
                adapter.clickListener = object : MovieAdapter.ItemOnClickListener {
                    override fun onclick(id: Int) {
                        val intent =
                            Intent(context, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_ID, id)
                        startActivity(intent)
                    }

                }
                adapter.notifyDataSetChanged()
            })

            with(binding?.rvFavMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _favMovieFragmentBinding = null
    }

}
package id.airham.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FragmentMovieBinding
import id.airham.moviecatalogue.ui.detail.DetailContentActivity
import id.airham.moviecatalogue.ui.detail.DetailContentActivity.Companion.EXTRA_ID
import id.airham.moviecatalogue.ui.detail.DetailContentActivity.Companion.EXTRA_TYPE
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar film
 */

class MovieFragment : Fragment() {
    private lateinit var fragmentMoviesBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMovieBinding
            .inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()


            with(fragmentMoviesBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
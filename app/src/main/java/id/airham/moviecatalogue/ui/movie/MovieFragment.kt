package id.airham.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FragmentMovieBinding
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_ID
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.viewmodel.ViewModelFactory
import id.airham.moviecatalogue.vo.Status

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar film
 */
class MovieFragment : Fragment() {
    private lateinit var fragmentMoviesBinding: FragmentMovieBinding

    private val TAG = this.javaClass.simpleName

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
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragmentMoviesBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.clickListener =
                                (object : MovieAdapter.ItemOnClickListener {
                                    override fun onclick(id: Int) {
                                        val intent =
                                            Intent(context, DetailMovieActivity::class.java)
                                        intent.putExtra(EXTRA_ID, id)
                                        startActivity(intent)
                                    }
                                })
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            showToast(context, "Something Error")
                        }
                    }
                }
            })

            with(fragmentMoviesBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
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
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_ID
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.viewmodel.ViewModelFactory
import id.airham.moviecatalogue.vo.Status

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar film
 */
class MovieFragment : Fragment() {

    private var _fragmentMoviesBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _fragmentMoviesBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
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
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
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
                            binding?.progressBar?.visibility = View.GONE
                            showToast(context, "Something Error")
                        }
                    }
                }
            })

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMoviesBinding = null
    }
}
package id.airham.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.airham.moviecatalogue.databinding.FragmentMovieBinding
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_DATA
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.vo.Status
import my.id.airham.core.data.Resource
import my.id.airham.core.utils.Notify.showToast

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar film
 */
@AndroidEntryPoint
class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModels()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding
            .inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()

            viewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> _binding.progressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                            movieAdapter.clickListener =
                                (object : MovieAdapter.ItemOnClickListener {
                                    override fun onclick(id: Int) {
                                        val intent =
                                            Intent(context, DetailMovieActivity::class.java)
                                        intent.putExtra(EXTRA_DATA, id)
                                        startActivity(intent)
                                    }
                                })
                            movieAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            showToast(context, "Something Error")
                        }
                    }
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
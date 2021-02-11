package id.airham.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FragmentTvShowBinding
import id.airham.moviecatalogue.ui.detail.DetailTvShowActivity
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.viewmodel.ViewModelFactory
import id.airham.moviecatalogue.vo.Status

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar tvShow
 */

class TvShowFragment : Fragment() {
    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTvShowBinding = FragmentTvShowBinding
            .inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> binding?.progressBar?.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            tvShowAdapter.submitList(movies.data)
                            tvShowAdapter.clickListener =
                                (object : TvShowAdapter.ItemOnClickListener {
                                    override fun onclick(id: Int) {
                                        val intent =
                                            Intent(context, DetailTvShowActivity::class.java)
                                        intent.putExtra(DetailTvShowActivity.EXTRA_ID, id)
                                        startActivity(intent)
                                    }
                                })
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            showToast(context, "Something Error")
                        }
                    }
                }
            })

            with(binding?.rvTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }
}
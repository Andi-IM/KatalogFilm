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
import id.airham.moviecatalogue.ui.detail.DetailContentActivity
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar tvShow
 */

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding
            .inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.notifyDataSetChanged()
                tvShowAdapter.clickListener = (object : TvShowAdapter.ItemOnClickListener{
                    override fun onclick(type: String, id: String) {
                        val intent = Intent(context, DetailContentActivity::class.java)
                        intent.putExtra(DetailContentActivity.EXTRA_TYPE, type)
                        intent.putExtra(DetailContentActivity.EXTRA_ID, id)
                        startActivity(intent)
                    }
                })
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}
package id.airham.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.airham.moviecatalogue.databinding.FragmentTvShowBinding
import id.airham.moviecatalogue.ui.detail.DetailMovieActivity
import id.airham.moviecatalogue.ui.detail.DetailTvShowActivity
import id.airham.moviecatalogue.utils.Notify.showToast
import id.airham.moviecatalogue.vo.Status

/**
 * Ini merupakan Fragment yang hanya menampilkan daftar tvShow
 */
@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private val viewModel: TvShowViewModel by viewModels()

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var adapter: TvShowAdapter

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

            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            adapter.submitList(tvShow.data)
                            adapter.clickListener =
                                (object : TvShowAdapter.ItemOnClickListener {
                                    override fun onclick(id: Int) {
                                        val intent =
                                            Intent(context, DetailTvShowActivity::class.java)
                                        intent.putExtra(DetailMovieActivity.EXTRA_DATA, id)
                                        startActivity(intent)
                                    }
                                })
                            adapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            showToast(context, "Something Error")
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapter
            }
        }
    }
}
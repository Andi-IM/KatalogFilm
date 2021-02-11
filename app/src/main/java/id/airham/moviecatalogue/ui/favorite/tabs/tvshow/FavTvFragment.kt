package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FavTvFragmentBinding
import id.airham.moviecatalogue.ui.detail.DetailTvShowActivity
import id.airham.moviecatalogue.ui.tvshow.TvShowAdapter
import id.airham.moviecatalogue.viewmodel.ViewModelFactory

class FavTvFragment : Fragment() {

    private var _favTvFragmentBinding: FavTvFragmentBinding? = null
    private val binding get() = _favTvFragmentBinding

    private lateinit var viewModel: FavTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favTvFragmentBinding = FavTvFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

        val adapter = TvShowAdapter()
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getFavorites().observe(viewLifecycleOwner, { tvShows ->
            binding?.progressBar?.visibility = View.GONE
            adapter.submitList(tvShows)
            adapter.clickListener =
                (object : TvShowAdapter.ItemOnClickListener {
                    override fun onclick(id: Int) {
                        val intent =
                            Intent(context, DetailTvShowActivity::class.java)
                        intent.putExtra(DetailTvShowActivity.EXTRA_ID, id)
                        startActivity(intent)
                    }
                })
            adapter.notifyDataSetChanged()
        })

        with(binding?.rvFavTvShow) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _favTvFragmentBinding = null
    }

}
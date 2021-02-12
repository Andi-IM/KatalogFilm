package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.airham.moviecatalogue.databinding.FavTvFragmentBinding
import id.airham.moviecatalogue.ui.tvshow.TvShowAdapter

@AndroidEntryPoint
class FavTvFragment : Fragment() {

    private var _favTvFragmentBinding: FavTvFragmentBinding? = null
    private val binding get() = _favTvFragmentBinding

    private val viewModel: FavTvViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favTvFragmentBinding = FavTvFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = TvShowAdapter()
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getFavorites().observe(viewLifecycleOwner, { tvShows ->
            binding?.progressBar?.visibility = View.GONE
            adapter.submitList(tvShows)
            adapter.notifyDataSetChanged()
        })

        binding?.rvFavTvShow?.layoutManager = LinearLayoutManager(context)
        binding?.rvFavTvShow?.setHasFixedSize(true)
        binding?.rvFavTvShow?.adapter = adapter
    }

}
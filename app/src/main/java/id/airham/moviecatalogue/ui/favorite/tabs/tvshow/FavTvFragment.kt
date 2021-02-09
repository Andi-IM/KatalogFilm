package id.airham.moviecatalogue.ui.favorite.tabs.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.airham.moviecatalogue.databinding.FavTvFragmentBinding
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
            adapter.setTvShows(tvShows)
            adapter.notifyDataSetChanged()
        })

        binding?.rvTvShow?.layoutManager = LinearLayoutManager(context)
        binding?.rvTvShow?.setHasFixedSize(true)
        binding?.rvTvShow?.adapter = adapter
    }

}
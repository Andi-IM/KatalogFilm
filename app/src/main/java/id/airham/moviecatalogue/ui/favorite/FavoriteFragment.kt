package id.airham.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.airham.moviecatalogue.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val sectionPagerAdapter =
                SectionsPagerAdapter(binding?.root?.context!!, childFragmentManager)
            binding?.viewPager?.adapter = sectionPagerAdapter
            binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteBinding = null
    }
}
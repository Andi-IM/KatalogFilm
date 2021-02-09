package id.airham.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.airham.moviecatalogue.data.source.ItemRepository
import id.airham.moviecatalogue.di.Injection
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailMovieViewModel
import id.airham.moviecatalogue.ui.detail.viewmodel.DetailTvViewModel
import id.airham.moviecatalogue.ui.movie.MovieViewModel
import id.airham.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mItemRepository: ItemRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mItemRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mItemRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(mItemRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mItemRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
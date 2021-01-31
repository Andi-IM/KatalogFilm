package id.airham.moviecatalogue.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import id.airham.moviecatalogue.data.TvShowEntity
import id.airham.moviecatalogue.utils.DataDummy

/**
 *  Kelas ini merupakan ViewModel yang digunakan untuk mendpatkan data MovieEntity dari
 *  objek {@link DataDummy}
 *
 *  setSelectedItem digunakan untuk menentukan item berdasarkan id
 *  id yang diberikan akan dicari pada method {@link getTvShow} yang akan mengembalikan data berupa
 *  Entitas TvShows.
 */

class DetailTvViewModel : ViewModel() {
    private lateinit var itemId: String
    fun setSelectedItem(itemId : String){
        this.itemId = itemId
    }

    fun getTvShow() : TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = DataDummy.generateTvs()
        for (tvShowEntity in tvShowEntities){
            if (tvShowEntity.id == itemId){
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}
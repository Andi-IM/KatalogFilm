package id.airham.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo

data class TvContent(
    @ColumnInfo(name = "tv-content")
    var tvContent: String?
)

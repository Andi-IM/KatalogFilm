package id.airham.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo

data class MovieContent(
    @ColumnInfo(name = "movie-content")
    var content: String?
)

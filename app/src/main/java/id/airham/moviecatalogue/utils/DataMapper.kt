package id.airham.moviecatalogue.utils

import id.airham.moviecatalogue.data.source.local.entity.MovieEntity
import id.airham.moviecatalogue.data.source.local.entity.TvShowEntity
import id.airham.moviecatalogue.data.source.remote.response.MovieResultsItem
import id.airham.moviecatalogue.data.source.remote.response.TvShowResultsItem

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movieEntity = MovieEntity(
                id = it.id,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
            movieList.add(movieEntity)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input: List<TvShowResultsItem>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShowEntity = TvShowEntity(
                id = it.id,
                originalName = it.originalName,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
            tvShowList.add(tvShowEntity)
        }
        return tvShowList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<MovieResultsItem> =
        input.map {
            MovieResultsItem(
                id = it.id,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
        }

    fun mapTvShowEntityToDomain(input: List<TvShowEntity>): List<TvShowResultsItem> =
        input.map {
            TvShowResultsItem(
                id = it.id,
                originalName = it.originalName,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
        }
}
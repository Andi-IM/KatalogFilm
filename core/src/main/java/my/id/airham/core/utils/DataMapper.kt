package my.id.airham.core.utils

import my.id.airham.core.data.source.local.entity.MovieEntity
import my.id.airham.core.data.source.local.entity.TvShowEntity
import my.id.airham.core.data.source.remote.response.MovieResultsItem
import my.id.airham.core.data.source.remote.response.TvShowResultsItem
import my.id.airham.core.domain.model.Movie
import my.id.airham.core.domain.model.TvShow

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

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
        }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                originalName = it.originalName,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
        }

    fun mapMovieEntityToDomain(input: MovieEntity): Movie =
        Movie(
            id = input.id,
            originalTitle = input.originalTitle,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            popularity = input.popularity
        )

    fun mapTvShowEntityToDomain(input: TvShowEntity): TvShow =
        TvShow(
            id = input.id,
            originalName = input.originalName,
            overview = input.overview,
            posterPath = input.posterPath,
            firstAirDate = input.firstAirDate,
            voteAverage = input.voteAverage,
            popularity = input.popularity
        )


    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        originalTitle = input.originalTitle,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        favorited = input.favorited,
        popularity = input.popularity
    )

    fun mapTvDomainToEntity(input: TvShow) = TvShowEntity(
        id = input.id,
        originalName = input.originalName,
        overview = input.overview,
        posterPath = input.posterPath,
        firstAirDate = input.firstAirDate,
        voteAverage = input.voteAverage,
        favorited = input.favorited,
        popularity = input.popularity
    )
}
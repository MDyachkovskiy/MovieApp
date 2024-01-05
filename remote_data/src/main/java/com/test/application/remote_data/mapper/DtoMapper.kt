package com.test.application.remote_data.mapper

import com.test.application.core.domain.collection.Collections
import com.test.application.core.domain.collection.Movie
import com.test.application.core.domain.collection.Poster
import com.test.application.core.domain.movieDetail.Budget
import com.test.application.core.domain.movieDetail.Country
import com.test.application.core.domain.movieDetail.Genre
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.MovieDetailsPoster
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.domain.movieDetail.Rating
import com.test.application.core.domain.movieDetail.SimilarMovy
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.core.domain.searchCollection.SearchResponse
import com.test.application.remote_data.dto.collection.CollectionsDTO
import com.test.application.remote_data.dto.collection.MovieDTO
import com.test.application.remote_data.dto.collection.PosterDTO
import com.test.application.remote_data.dto.movieDetails.BudgetDTO
import com.test.application.remote_data.dto.movieDetails.CountryDTO
import com.test.application.remote_data.dto.movieDetails.GenreDTO
import com.test.application.remote_data.dto.movieDetails.MovieDetailsDTO
import com.test.application.remote_data.dto.movieDetails.MovieDetailsPosterDTO
import com.test.application.remote_data.dto.movieDetails.PersonDTO
import com.test.application.remote_data.dto.movieDetails.RatingDTO
import com.test.application.remote_data.dto.movieDetails.SimilarMovyDTO
import com.test.application.remote_data.dto.search.SearchDTO
import com.test.application.remote_data.dto.search.SearchMovieDTO

fun CollectionsDTO.toDomain(): Collections {
    return Collections(
        movie = movie.map { it.toDomain() },
        limit = limit,
        page = page,
        pages = pages,
        total = total
    )
}

fun MovieDTO.toDomain(): Movie {
    return Movie(
        id = id,
        rating = rating.toDomain(),
        year = year,
        genres = this.genres?.map { genreDTO -> genreDTO.toDomain() },
        name = name ?: "",
        poster = poster?.toDomain() ?: Poster(),
        description = description
    )
}

fun PosterDTO.toDomain(): Poster {
    return Poster(
        previewUrl = previewUrl ?: "",
        url = url ?: ""
    )
}

fun SearchMovieDTO.toDomain(): SearchMovie {
    return SearchMovie(
        id = id,
        name = name,
        poster = poster,
        description = description
    )
}

fun SearchDTO.toDomain(): SearchResponse {
    return SearchResponse(
        movies = movies.map { it.toDomain() },
        limit = limit,
        page = page,
        pages = pages,
        total = total
    )
}

fun MovieDetailsDTO.toDomain(): MovieDetails {
    return MovieDetails(
        budget = this.budget?.toDomain() ?: Budget("", 0),
        countries = this.countries?.map { countryDTO -> countryDTO.toDomain() },
        description = this.description,
        genres = this.genres?.map { genreDTO -> genreDTO.toDomain() },
        id = this.id,
        movieLength = this.movieLength,
        name = this.name,
        persons = this.persons.map { personDTO ->  personDTO.toDomain() },
        movieDetailsPoster = this.poster?.toDomain(),
        rating = this.rating?.toDomain(),
        similarMovies = this.similarMovies?.map { similarMovyDTO -> similarMovyDTO.toDomain() },
        type = this.type,
        year = this.year
    )
}

fun BudgetDTO.toDomain(): Budget {
    return Budget(
        currency = this.currency,
        value = this.value
    )
}

fun CountryDTO.toDomain() : Country {
    return Country(
        name = this.name
    )
}

fun GenreDTO.toDomain(): Genre {
    return Genre (
        name = this.name
    )
}

fun PersonDTO.toDomain(): Person {
    return Person(
        description = this.description,
        enName = this.enName,
        enProfession = this.enProfession,
        id = this.id ,
        name = this.name,
        photo = this.photo,
        profession = this.profession
    )
}

fun MovieDetailsPosterDTO.toDomain() : MovieDetailsPoster {
    return MovieDetailsPoster(
        previewUrl = this.previewUrl,
        url = this.url
    )
}

fun RatingDTO.toDomain(): Rating {
    return Rating(
        await = this.await,
        filmCritics = this.filmCritics,
        imdb = this.imdb,
        kp = this.kp,
        russianFilmCritics = this.russianFilmCritics
    )
}

fun SimilarMovyDTO.toDomain(): SimilarMovy {
    return SimilarMovy(
        alternativeName = this.alternativeName,
        enName = this.enName,
        id = this.id,
        name = this.name,
        movieDetailsPoster = this.poster.toDomain(),
        type = this.type
    )
}
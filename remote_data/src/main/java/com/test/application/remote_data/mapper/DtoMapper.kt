package com.test.application.remote_data.mapper

import com.test.application.core.domain.collection.Collections
import com.test.application.core.domain.collection.Movie
import com.test.application.core.domain.collection.Poster
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.core.domain.searchCollection.SearchResponse
import com.test.application.remote_data.dto.collection.CollectionsDTO
import com.test.application.remote_data.dto.collection.MovieDTO
import com.test.application.remote_data.dto.collection.PosterDTO
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
        name = name,
        poster = poster.toDomain(),
        description = description
    )
}

fun PosterDTO.toDomain(): Poster {
    return Poster(
        previewUrl = previewUrl,
        url = url
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
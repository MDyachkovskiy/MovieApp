package com.test.application.remote_data.api

import com.test.application.remote_data.dto.collection.CollectionsDTO
import com.test.application.remote_data.dto.movieDetails.MovieDetailsDTO
import com.test.application.remote_data.dto.personDetailsDTO.PersonDetailsDTO
import com.test.application.remote_data.dto.search.SearchDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskService {
    @GET("$MOVIE_DETAILS/{movieId}")
    fun getMovieAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Path("movieId") id : Int?
    ) : Deferred<MovieDetailsDTO>

    @GET(PERSON_END_POINT)
    fun getPersonAsync (
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("id") id : Int?,
        @Query("selectFields") selectedFields: String? = PERSON_SELECTED_FIELDS
    ) : Deferred<PersonDetailsDTO>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getTop250CollectionAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("sortFields") sort: String? = KINOPOISK_RATING,
        @Query("sortType") sortType: Int? = DENOMINATION_SORT,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("type") type: String? = MOVIE_TYPE,
        @Query("top250") top250: String? = "!null",
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsDTO>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getTopTvShowsCollectionAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("sortFields") sort: String? = KINOPOISK_RATING,
        @Query("sortType") sortType: Int? = DENOMINATION_SORT,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("type") type: String? = TV_SHOW_TYPE,
        @Query("top250") top250: String? = "!null",
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsDTO>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getUpComingCollectionAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("movie") movie: String? = "!null",
        @Query("year") year: Int? = UPCOMING_YEAR,
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsDTO>

    @GET(MOVIE_SEARCH)
    fun getSearchMovieAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("query") query : String,
        @Query("page") page: Int? = 1
    ) : Deferred<SearchDTO>
}
package com.test.application.remote_data.api

import com.test.application.remote_data.dto.collection.CollectionsDTO
import com.test.application.remote_data.dto.movieDetails.MovieDetailsDTO
import com.test.application.remote_data.dto.personDetailsDTO.PersonDetailsDTO
import com.test.application.remote_data.dto.search.SearchDTO
import com.test.application.remote_data.utils.COLLECTION_SELECTED_FIELDS
import com.test.application.remote_data.utils.DENOMINATION_SORT
import com.test.application.remote_data.utils.KINOPOISK_RATING
import com.test.application.remote_data.utils.MOVIE_DETAILS
import com.test.application.remote_data.utils.MOVIE_SEARCH
import com.test.application.remote_data.utils.MOVIE_TYPE
import com.test.application.remote_data.utils.PERSON_END_POINT
import com.test.application.remote_data.utils.PERSON_SELECTED_FIELDS
import com.test.application.remote_data.utils.RESPONSE_QUANTITY
import com.test.application.remote_data.utils.TOP_COLLECTIONS_END_POINT
import com.test.application.remote_data.utils.TV_SHOW_TYPE
import com.test.application.remote_data.utils.UPCOMING_YEAR
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
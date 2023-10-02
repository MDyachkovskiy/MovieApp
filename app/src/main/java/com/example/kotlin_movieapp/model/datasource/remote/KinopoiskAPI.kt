package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.COLLECTION_SELECTED_FIELDS
import com.example.kotlin_movieapp.utils.DENOMINATION_SORT
import com.example.kotlin_movieapp.utils.KINOPOISK_RATING
import com.example.kotlin_movieapp.utils.MOVIE_DETAILS
import com.example.kotlin_movieapp.utils.MOVIE_SEARCH
import com.example.kotlin_movieapp.utils.MOVIE_TYPE
import com.example.kotlin_movieapp.utils.PERSON_END_POINT
import com.example.kotlin_movieapp.utils.RESPONSE_QUANTITY
import com.example.kotlin_movieapp.utils.TOP_COLLECTIONS_END_POINT
import com.example.kotlin_movieapp.utils.TV_SHOW_TYPE
import com.example.kotlin_movieapp.utils.UPCOMING_YEAR
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskAPI {
    @GET("$MOVIE_DETAILS/{movieId}")
    fun getMovieAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Path("movieId") id : Int?
    ) : Deferred<MovieDetailsResponse>

    @GET(PERSON_END_POINT)
    fun getPerson (
        @Query("search") id : Int?
    ) : Call<PersonDTO>

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
    ) : Deferred<CollectionsResponse>

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
    ) : Deferred<CollectionsResponse>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getUpComingCollectionAsync(
        @Header("x-api-key") apiKey: String = "ZSHR6KE-1B84QK2-G3HSAJY-V6F6YBB",
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("movie") movie: String? = "!null",
        @Query("year") year: Int? = UPCOMING_YEAR,
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsResponse>

    @GET(MOVIE_SEARCH)
    fun getSearchMovieAsync(
        @Query("query") query : String,
    ) : Deferred<CollectionsResponse>
}
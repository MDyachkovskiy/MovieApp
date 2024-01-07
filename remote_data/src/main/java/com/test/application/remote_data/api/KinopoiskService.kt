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
import com.test.application.remote_data.utils.NOT_NULL_COLLECTION_FIELDS
import com.test.application.remote_data.utils.PERSON_END_POINT
import com.test.application.remote_data.utils.PERSON_SELECTED_FIELDS
import com.test.application.remote_data.utils.RESPONSE_QUANTITY
import com.test.application.remote_data.utils.TOP_COLLECTIONS_END_POINT
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskService {
    @GET("$MOVIE_DETAILS/{movieId}")
    fun getMovieAsync(
        @Path("movieId") id : String
    ) : Deferred<MovieDetailsDTO>

    @GET(PERSON_END_POINT)
    fun getPersonAsync (
        @Query("id") id : Int?,
        @Query("selectFields") selectedFields: String? = PERSON_SELECTED_FIELDS
    ) : Deferred<PersonDetailsDTO>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getTop250CollectionAsync(
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
        @Query("sortFields") sort: String? = KINOPOISK_RATING,
        @Query("sortType") sortType: Int? = DENOMINATION_SORT,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("networks.items.name") streamingName: String?,
        @Query("notNullFields") notNullFields: String? = NOT_NULL_COLLECTION_FIELDS,
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsDTO>

    @GET(TOP_COLLECTIONS_END_POINT)
    fun getUpComingCollectionAsync(
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = RESPONSE_QUANTITY,
        @Query("type") type: String? = MOVIE_TYPE,
        @Query("premiere.world") premiereWorld: String? = "",
        @Query("notNullFields") notNullFields: String? = NOT_NULL_COLLECTION_FIELDS,
        @Query("selectFields") selectedFields: String? = COLLECTION_SELECTED_FIELDS
    ) : Deferred<CollectionsDTO>

    @GET(MOVIE_SEARCH)
    fun getSearchMovieAsync(
        @Query("query") query : String,
        @Query("page") page: Int? = 1
    ) : Deferred<SearchDTO>
}
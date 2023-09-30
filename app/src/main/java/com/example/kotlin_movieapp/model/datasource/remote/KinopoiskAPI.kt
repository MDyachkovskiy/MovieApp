package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.COLLECTION_SELECTED_FIELDS
import com.example.kotlin_movieapp.utils.DENOMINATION_SORT
import com.example.kotlin_movieapp.utils.KINOPOISK_RATING
import com.example.kotlin_movieapp.utils.MOVIE_END_POINT
import com.example.kotlin_movieapp.utils.MOVIE_TYPE
import com.example.kotlin_movieapp.utils.PERSON_END_POINT
import com.example.kotlin_movieapp.utils.RESPONSE_QUANTITY
import com.example.kotlin_movieapp.utils.SEARCH_END_POINT
import com.example.kotlin_movieapp.utils.TOP_COLLECTIONS_END_POINT
import com.example.kotlin_movieapp.utils.TV_SHOW_TYPE
import com.example.kotlin_movieapp.utils.UPCOMING_YEAR
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KinopoiskAPI {
    @GET(MOVIE_END_POINT)
    fun getMovie(
        @Query("search") id : Int?
    ) : Call<MovieDTO>

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

    @GET(SEARCH_END_POINT)
    fun getAdultCyrillicSearchCollection(
        @Query("field") ratingField : String,
        @Query("search") rate: String,
        @Query("field") nameField: String,
        @Query("search") name: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int,
        @Query("field") ageRating6: String,
        @Query("search") age6: Int,
        @Query("field") ageRating12: String,
        @Query("search") age12: Int,
        @Query("field") ageRating16: String,
        @Query("search") age16: Int,
        @Query("field") ageRating18: String,
        @Query("search") age18: Int,
        @Query("field") ageRatingNull: String,
        @Query("selectFields") selectFields: String,
    ) : Call<SearchResponse>

    @GET(SEARCH_END_POINT)
    fun getCyrillicSearchCollection(
        @Query("field") ratingField : String,
        @Query("search") rate: String,
        @Query("field") nameField: String,
        @Query("search") name: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int,
        @Query("field") ageRating6: String,
        @Query("search") age6: Int,
        @Query("field") ageRating12: String,
        @Query("search") age12: Int,
        @Query("field") ageRating16: String,
        @Query("search") age16: Int,
        @Query("field") ageRatingNull: String,
        @Query("selectFields") selectFields: String,
    ) : Call<SearchResponse>

    @GET(SEARCH_END_POINT)
    fun getAdultLatinSearchCollection(
        @Query("field") ratingField : String,
        @Query("search") rate: String,
        @Query("field") englishNameField: String,
        @Query("search") name: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int,
        @Query("field") ageRating6: String,
        @Query("search") age6: Int,
        @Query("field") ageRating12: String,
        @Query("search") age12: Int,
        @Query("field") ageRating16: String,
        @Query("search") age16: Int,
        @Query("field") ageRating18: String,
        @Query("search") age18: Int,
        @Query("field") ageRatingNull: String,
        @Query("selectFields") selectFields: String,
    ) : Call<SearchResponse>

    @GET(SEARCH_END_POINT)
    fun getLatinSearchCollection(
        @Query("field") ratingField : String,
        @Query("search") rate: String,
        @Query("field") englishNameField: String,
        @Query("search") name: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int,
        @Query("field") ageRating6: String,
        @Query("search") age6: Int,
        @Query("field") ageRating12: String,
        @Query("search") age12: Int,
        @Query("field") ageRating16: String,
        @Query("search") age16: Int,
        @Query("field") ageRatingNull: String,
        @Query("selectFields") selectFields: String,
    ) : Call<SearchResponse>

}
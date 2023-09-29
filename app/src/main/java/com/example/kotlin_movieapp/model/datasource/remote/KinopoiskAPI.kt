package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.MOVIE_END_POINT
import com.example.kotlin_movieapp.utils.PERSON_END_POINT
import com.example.kotlin_movieapp.utils.POPULAR_NOW_END_POINT
import com.example.kotlin_movieapp.utils.SEARCH_END_POINT
import com.example.kotlin_movieapp.utils.TOP250_END_POINT
import com.example.kotlin_movieapp.utils.TOP_TV_SHOWS_END_POINT
import retrofit2.Call
import retrofit2.http.GET
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

    @GET(TOP250_END_POINT)
    fun getTop250Collection(
    ) : Call<Top250Response>

    @GET(TOP_TV_SHOWS_END_POINT)
    fun getTopTvShowsCollection(
    ) : Call<TopTvShowsResponse>

    @GET(POPULAR_NOW_END_POINT)
    fun getUpComingCollection(
    ) : Call<UpComingResponse>

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
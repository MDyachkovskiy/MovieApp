package com.example.kotlin_movieapp.utils

import com.example.kotlin_movieapp.BuildConfig

const val REQUEST_ERROR = "Ошибка запроса на сервер"

const val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY

const val PERSON_END_POINT = "v1/person"

const val TOP_COLLECTIONS_END_POINT = "v1.3/movie"
const val MOVIE_DETAILS = "v1.3/movie"
const val MOVIE_SEARCH = "v1.2/movie/search"
const val KINOPOISK_RATING = "rating.kp"
const val DENOMINATION_SORT = -1
const val RESPONSE_QUANTITY = 10
const val MOVIE_TYPE = "movie"
const val TV_SHOW_TYPE = "tv-series"
const val COLLECTION_SELECTED_FIELDS = "id name poster"
const val PERSON_SELECTED_FIELDS = "age birthPlace.value birthday id name photo profession.value"
const val UPCOMING_YEAR = 2023
const val KINOPOISK_DOMAIN = "https://api.kinopoisk.dev/"

const val KEY_BUNDLE_MOVIE = "key_movie"
const val KEY_BUNDLE_PERSON = "key_person"

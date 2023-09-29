package com.example.kotlin_movieapp.utils

import com.example.kotlin_movieapp.BuildConfig

const val SERVER_ERROR = "Ошибка сервера"
const val REQUEST_ERROR = "Ошибка запроса на сервер"
const val CORRUPTED_DATA = "Неполные данные"

const val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY

const val MOVIE_END_POINT = "movie?$KINOPOISK_TOKEN&field=id"

const val PERSON_END_POINT = "person?$KINOPOISK_TOKEN&field=id"

const val TOP250_END_POINT =
    "movie/?$KINOPOISK_TOKEN" +
            "&field=top250" +
            "&search=!null" +
            "&sortField=top250" +
            "&field=type" +
            "&search=movie" +
            "&moviesLimit=20" +
            "&selectFields=id name top250 poster"

const val TOP_TV_SHOWS_END_POINT =
    "movie/?$KINOPOISK_TOKEN" +
            "&field=top250" +
            "&search=!null" +
            "&sortField=top250" +
            "&field=type" +
            "&search=tv-series" +
            "&selectFields=id name top250 poster type"

const val POPULAR_NOW_END_POINT =
    "collection/?$KINOPOISK_TOKEN" +
            "&search=top_items_all" +
            "&field=collectionId" +
            "&selectFields=id name top250 poster type"

const val SEARCH_END_POINT = "movie/?$KINOPOISK_TOKEN"

const val KINOPOISK_DOMAIN = "https://api.kinopoisk.dev/"

const val RATING_FIELD = "rating.kp"

const val NAME_FIELD = "name"

const val ENLISH_NAME_FIELD = "alternativeName"

const val AGE_RATING = "ageRating"

const val SORT_FIELD = "id name top250 poster type rating.kp description"
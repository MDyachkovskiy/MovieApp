package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.model.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.collectionResponse.TopTvShowsResponse

interface CollectionsRepository {
    fun getTop250CollectionFromServer(callback : retrofit2.Callback<Top250Response>)
    fun getTopTvShowsCollectionFromServer(callback : retrofit2.Callback<TopTvShowsResponse>)
}
package com.example.kotlin_movieapp.model.repository.collections

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse

interface CollectionsRepository {
    fun getTop250CollectionFromServer(callback : retrofit2.Callback<Top250Response>)
    fun getTopTvShowsCollectionFromServer(callback : retrofit2.Callback<TopTvShowsResponse>)
    fun getUpComingCollectionFromServer(callback: retrofit2.Callback<UpComingResponse>)
}
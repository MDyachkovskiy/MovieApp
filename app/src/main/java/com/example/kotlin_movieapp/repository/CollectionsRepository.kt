package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.CollectionItem

interface CollectionsRepository {
    fun getTop250CollectionFromServer(callback : retrofit2.Callback<List<CollectionItem>>)
}
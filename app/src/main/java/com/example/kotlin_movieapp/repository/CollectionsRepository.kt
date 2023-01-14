package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.Top250Response

interface CollectionsRepository {
    fun getTop250CollectionFromServer(callback : retrofit2.Callback<Top250Response>)
}
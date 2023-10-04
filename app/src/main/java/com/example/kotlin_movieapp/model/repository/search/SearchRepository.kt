package com.example.kotlin_movieapp.model.repository.search

import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Doc
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchCollection(name: String): Flow<PagingData<Doc>>
}
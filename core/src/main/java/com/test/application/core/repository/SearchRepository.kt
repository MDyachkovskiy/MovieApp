package com.test.application.core.repository

import androidx.paging.PagingData
import com.test.application.core.domain.searchCollection.SearchMovie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchCollection(name: String): Flow<PagingData<SearchMovie>>
}
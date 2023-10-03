package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.view.home.top250Movie.Top250PagingSource
import kotlinx.coroutines.flow.Flow

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override fun getTop250CollectionFromServer(): Flow<PagingData<Doc>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {Top250PagingSource(remoteDataSource.getKinopoiskAPI())}
        ).flow
    }

    override suspend fun getTopTvShowsCollectionFromServer(): AppState {
        return parseApiCall { remoteDataSource.getTopTvShowsCollection() }
    }

    override suspend fun getUpComingCollectionFromServer(): AppState {
       return parseApiCall { remoteDataSource.getUpComingCollection() }
    }
    private suspend fun <T> parseApiCall(apiCall: suspend() -> T): AppState {
        return try {
            AppState.Success(apiCall.invoke())
        } catch(e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}
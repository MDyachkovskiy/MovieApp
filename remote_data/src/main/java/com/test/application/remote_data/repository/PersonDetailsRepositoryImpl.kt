package com.test.application.remote_data.repository

import com.test.application.core.utils.AppState.AppState
import com.test.application.core.repository.PersonDetailsRepository
import com.test.application.core.utils.REQUEST_ERROR
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.mapper.toDomain

class PersonDetailsRepositoryImpl (
    private val kinopoiskService: KinopoiskService
) : PersonDetailsRepository {

    override suspend fun getPersonDetailsFromRemoteServer(personId: Int): AppState {
        return try {
            val responseDTO = kinopoiskService.getPersonAsync(id = personId).await()
            val response = responseDTO.toDomain()
            AppState.Success(response)
        } catch (e:Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}
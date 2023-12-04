package com.test.application.remote_data.repository

import com.test.application.core.utils.AppState.AppState
import com.test.application.core.repository.PersonDetailsRepository
import com.test.application.core.utils.REQUEST_ERROR
import com.test.application.remote_data.api.KinopoiskService

class PersonDetailsRepositoryImpl (
    private val kinopoiskService: KinopoiskService
) : PersonDetailsRepository {

    override suspend fun getPersonDetailsFromRemoteServer(personId: Int): AppState {
        return try {
            val response = kinopoiskService.getPersonAsync(id = personId)
            //нужен мапер
            AppState.Success(response)
        } catch (e:Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}
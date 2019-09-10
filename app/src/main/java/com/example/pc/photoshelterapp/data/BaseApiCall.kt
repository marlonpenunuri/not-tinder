package com.example.pc.photoshelterapp.data

import com.example.pc.photoshelterapp.R
import retrofit2.Response
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


/**
 *This class is an abstraction for all retrofit Api petitions, it only serves the purpose of
 *saving code
**/

open class BaseApiCall {

    @Inject
    lateinit var retrofit: Retrofit
    suspend fun <T : Any> callApi(call: suspend () -> Response<T>): ParseResult {
        try {
            val response = call.invoke()
            if (!response.isSuccessful) {
                val errorStringId = when(response.code()){
                    404 -> R.string.not_found_error
                    503 -> R.string.service_unavailable_error
                    else ->R.string.unknown_error
                }
                return Error(errorStringId)
            }
            return Success(response.body())

        } catch (ex: Exception) {
            return when (ex) {
                is ConnectException -> Error(R.string.connection_error)
                is UnknownHostException -> Error(R.string.host_error)
                else -> Error(R.string.unknown_error)
            }
        }
    }
}
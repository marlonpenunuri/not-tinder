package com.example.pc.photoshelterapp.data

import com.example.pc.photoshelterapp.R
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


open class BaseApiCall {

    @Inject
    lateinit var retrofit: Retrofit
    suspend fun <T : Any> callApi(call: suspend () -> Response<T>): ParseResult {
        try {
            val response = call.invoke()
            if (!response.isSuccessful) {
                return Error("Error")
//                val error = parseError(response)
//                return when (error.status) {
//                    else -> Error("Error")
//                }
            }
            return Success(response.body())

        } catch (ex: Exception) {
            return when (ex) {
                is ConnectException -> Error("Error")
                is UnknownHostException -> Error("Error")
                else -> Error("Error")
            }
        }
    }

//    private fun parseError(response: Response<*>): BaseError {
//        val converter = retrofit.responseBodyConverter<BaseError>(
//            BaseError::class.java,
//            BaseError::class.java.annotations
//        )
//        return try {
//            converter.convert(response.errorBody()!!)!!
//        } catch (e: IOException) {
//            BaseError()
//        }
//    }
}
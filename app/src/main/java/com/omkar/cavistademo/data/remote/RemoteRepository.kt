package com.omkar.cavistademo.data.remote

import android.content.Context
import com.omkar.cavistademo.BuildConfig
import com.omkar.cavistademo.data.model.SearchResponse
import com.omkar.cavistademo.data.remote.Network.Utils.isConnected
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val context: Context) {

    suspend fun requestImages(page: Int, searchTerm: String): Resource<SearchResponse> {
        val imageListService =
            serviceGenerator.createService(GetImageListService::class.java, BuildConfig.BASE_URL)
        val response = processCall { imageListService.fetchImageList(page, searchTerm) }
        return if (response is SearchResponse) {
            Resource.Success(data = response)
        } else {
            Resource.DataError(error = response as AppError)
        }

    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!isConnected(context)) {
            return AppError(AppError.NO_INTERNET_CONNECTION, "No internet")
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                return AppError(responseCode, "Request failed")
            }
        } catch (e: IOException) {
            return AppError(AppError.NETWORK_ERROR, "Network Error")
        }
    }

}

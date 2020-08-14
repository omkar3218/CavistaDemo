package com.omkar.cavistademo.data.remote

import com.omkar.cavistademo.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetImageListService {
    @GET("{page}")
    suspend fun fetchImageList(
        @Path("page") page: Int,
        @Query("q") searchTerm: String
    ): Response<SearchResponse>
}
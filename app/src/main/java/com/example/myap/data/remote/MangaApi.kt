package com.example.myap.data.remote

import com.example.myap.data.response.MangaListResponse
import com.example.myap.data.response.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val NUMBER_OF_MANGA = 21
interface MangaApi {

    @GET("top/manga")
    suspend fun getListOfManga(
        @Query("limit") count: Int = NUMBER_OF_MANGA
    ): MangaListResponse

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") id: Int,
    ): MangaResponse
}

package com.example.myap.di

import com.example.myap.data.remote.MangaApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        GsonConverterFactory.create()
    }
    single {
        provideHttpClient()
    }
    single {
        provideRetrofit(
            get(),
            get()
        )
    }
    single {
        provideWeatherApi(
            get()
        )
    }
}
private fun provideWeatherApi(
    retrofit: Retrofit
): MangaApi = retrofit.create(MangaApi::class.java)

private fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .addConverterFactory(gsonFactory)
    .baseUrl("https://api.jikan.moe/v4/")
    .build()

private fun provideHttpClient(
): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10L, TimeUnit.SECONDS)
    .build()

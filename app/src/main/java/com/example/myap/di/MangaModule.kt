package com.example.myap.di

import com.example.myap.data.remote.MangaApi
import com.example.myap.data.remote.MangaRepositoryImpl
import com.example.myap.domain.GetMangaListUseCase
import com.example.myap.domain.GetMangaUseCase
import com.example.myap.domain.MangaRepository
import org.koin.dsl.module

val mangaModule = module {
    single {
        provideMangaRepository(
            mangaApi = get()
        )
    }
    single {
        provideGetMangaUseCase(
            mangaRepository = get()
        )
    }
    single {
        provideGetMangaListUseCase(
            mangaRepository = get()
        )
    }
}

private fun provideMangaRepository(
    mangaApi: MangaApi
): MangaRepository = MangaRepositoryImpl(mangaApi)

private fun provideGetMangaUseCase(
    mangaRepository: MangaRepository
): GetMangaUseCase = GetMangaUseCase(mangaRepository)

private fun provideGetMangaListUseCase(
    mangaRepository: MangaRepository
): GetMangaListUseCase = GetMangaListUseCase(mangaRepository)

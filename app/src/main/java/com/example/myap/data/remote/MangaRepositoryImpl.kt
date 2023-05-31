package com.example.myap.data.remote

import com.example.myap.data.mapper.toMangaListModel
import com.example.myap.data.mapper.toMangaModel
import com.example.myap.domain.MangaListModel
import com.example.myap.domain.MangaModel
import com.example.myap.domain.MangaRepository

class MangaRepositoryImpl(
    private val api: MangaApi
): MangaRepository {
    override suspend fun getMangaById(id: Int): MangaModel {
        val response = api.getMangaById(id)
        return response.toMangaModel()
    }

    override suspend fun getMangaList(): MangaListModel {
        val response = api.getListOfManga()
        return response.toMangaListModel()
    }
}

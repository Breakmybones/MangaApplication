package com.example.myap.data.mapper

import com.example.myap.data.response.MangaListResponse
import com.example.myap.domain.MangaListModel

fun MangaListResponse.toMangaListModel() = MangaListModel(
    mangaList = data.map { dataMapper(it) }
)

package com.example.myap.data.mapper

import com.example.myap.data.response.Data
import com.example.myap.data.response.MangaResponse
import com.example.myap.domain.MangaModel

fun MangaResponse.toMangaModel(): MangaModel = dataMapper(data)

fun dataMapper(data: Data?): MangaModel = MangaModel(
    id = data?.malId,
    name = data?.title,
    icon = data?.images?.jpg?.imageUrl,
    description = data?.synopsis,
    year = data?.published?.prop?.from?.year,
    type = data?.type
)

package com.example.myap.domain

class GetMangaListUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke(): MangaListModel {
        return mangaRepository.getMangaList()
    }
}

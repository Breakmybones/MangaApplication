package com.example.myap.domain

class GetMangaUseCase(
    val repository: MangaRepository
) {
    suspend operator fun invoke(id: Int): MangaModel {
        return repository.getMangaById(id)
    }
}

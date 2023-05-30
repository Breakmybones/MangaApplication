package com.example.myap.presentation.presenter

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myap.domain.GetMangaUseCase
import com.example.myap.domain.MangaModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(
    private val getMangaUseCase: GetMangaUseCase
): ViewModel() {

    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState>
        get() = _state.asStateFlow()

    fun event(detailEvent: DetailsEvent) {
        when (detailEvent) {
            is DetailsEvent.OnLoadManga -> onLoadManga(detailEvent.mangaId)
        }
    }

    private fun onLoadManga(
        mangaId: Int
    ) {
        viewModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        loading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        manga = getMangaUseCase.invoke(mangaId)
                    )
                )
            }
            catch (error: Throwable) {
                Timber.e(error.toString())
            }
            finally {
                _state.emit(
                    _state.value.copy(
                        loading = false
                    )
                )
            }
        }
    }

}

@Immutable
data class DetailsViewState(
    val loading: Boolean = false,
    val manga: MangaModel? = null,
)

sealed interface DetailsEvent {
    data class OnLoadManga(val mangaId: Int) : DetailsEvent
}

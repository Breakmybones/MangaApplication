package com.example.myap.presentation.presenter

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myap.domain.GetMangaListUseCase
import com.example.myap.domain.MangaListModel
import com.example.myap.domain.MangaModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getMangaListUseCase: GetMangaListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<HomeAction?>()
    val action: SharedFlow<HomeAction?>
        get() = _action.asSharedFlow()

    fun event(homeEvent: HomeEvent) {
        when (homeEvent) {
            is HomeEvent.OnLoadManga -> onLoadManga()
            is HomeEvent.OnMangaClick -> onMangaClick(homeEvent.manga)
        }
    }

    private fun onLoadManga() {
        viewModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        loading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        mangaList = getMangaListUseCase.invoke()
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

    private fun onMangaClick(manga: MangaModel) {
        viewModelScope.launch {
            _action.emit(
                HomeAction.Navigate(mangaId = manga.id ?: 0)
            )
        }
    }

}

@Immutable
data class HomeViewState(
    val mangaList: MangaListModel? = null,
    val manga: MangaModel? = null,
    val loading: Boolean = false
)

sealed interface HomeAction {
    data class Navigate(val mangaId: Int): HomeAction
}

sealed interface HomeEvent {
    data class OnMangaClick(val manga: MangaModel): HomeEvent
    object OnLoadManga: HomeEvent
}

package com.example.myap.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.myap.domain.MangaModel
import com.example.myap.presentation.presenter.HomeAction
import com.example.myap.presentation.presenter.HomeEvent
import com.example.myap.presentation.presenter.HomeViewState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myap.presentation.presenter.HomeViewModel
import com.example.myap.presentation.theme.MangaTheme
import org.koin.androidx.compose.koinViewModel
import com.example.myap.R


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    HomeContent(viewState = state.value, eventHandler = viewModel::event)

    HomeActions(navController = navController, viewAction = action)

}
@Composable
fun HomeContent(
    viewState: HomeViewState,
    eventHandler: (HomeEvent) -> Unit
) {
    if (viewState.mangaList == null) {
        eventHandler.invoke(HomeEvent.OnLoadManga)
        if (viewState.loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
    else {
        MangaList(viewState = viewState, eventHandler = eventHandler)
    }
}

@Composable
fun HomeActions(
    navController: NavController,
    viewAction: HomeAction?
) {
    val prefix = stringResource(id = R.string.details_prefix)
    LaunchedEffect(viewAction) {
        when(viewAction) {
            null -> Unit
            is HomeAction.Navigate -> {
                navController.navigate(prefix + viewAction.mangaId)
            }
        }
    }
}

@Composable
fun MangaItem(
    mangaModel: MangaModel,
    onClick: (MangaModel) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MangaTheme.colors.primaryBackground)
            .clickable {
                onClick.invoke(mangaModel)
            },
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            SubcomposeAsyncImage(
                model = mangaModel.icon,
                contentDescription = null
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(
                            vertical = 48.dp,
                            horizontal = 36.dp
                        )
                    )
                } else {
                    SubcomposeAsyncImageContent(modifier = Modifier.padding(12.dp))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                mangaModel.name?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Left,
                        style = MangaTheme.typography.manga
                    )
                }
                mangaModel.type?.let {
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        text = it,
                        textAlign = TextAlign.Left,
                        style = MangaTheme.typography.manga
                        )
                }
            }
        }
    }
}

@Composable
fun MangaList(
    viewState: HomeViewState,
    eventHandler: (HomeEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        viewState.mangaList?.mangaList?.let { list ->
            items(
                list.size,
                key = { list[it].name.toString()}) { position ->
                MangaItem(
                    mangaModel = list[position],
                    onClick = {eventHandler.invoke(HomeEvent.OnMangaClick(it))})
            }
        }
    }
}

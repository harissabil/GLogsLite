package com.harissabil.glogslite.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.harissabil.glogslite.R
import com.harissabil.glogslite.data.remote.response.ResultsItem
import com.harissabil.glogslite.di.Injection
import com.harissabil.glogslite.helper.Platforms
import com.harissabil.glogslite.ui.ViewModelFactory
import com.harissabil.glogslite.ui.components.ErrorMessage
import com.harissabil.glogslite.ui.components.GameItem
import com.harissabil.glogslite.ui.components.PaginationLoading
import com.harissabil.glogslite.ui.components.PlatformChip
import com.harissabil.glogslite.ui.components.Search
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToSearch: (String) -> Unit,
    navigateToDetail: (String, String) -> Unit
) {
    val games = viewModel.games.collectAsLazyPagingItems()
    val selectedPlatform = viewModel.platform.collectAsState()

    val query by viewModel.query
    var active by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            Row {
                Search(
                    query = query,
                    onQueryChange = viewModel::search,
                    onSearch = if (query.isNotEmpty()) {
                        {
                            active = false
                            navigateToSearch(query)
                            viewModel.search("")
                        }
                    } else {
                        {
                            active = true
                        }
                    },
                    active = active,
                    onActiveChange = { active = it },
                    onCloseIcon = {
                        if (query.isNotEmpty()) {
                            viewModel.search("")
                        } else {
                            active = false
                        }
                    },
                )
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = modifier,
    ) { innerPadding ->
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember(listState) {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Column {
                PlatformChip(
                    modifier = modifier.padding(start = 16.dp),
                    itemList = Platforms.getAllGames(),
                    onClick = { platform ->
                        viewModel.setPlatform(platform)
                    },
                    selectedItem = selectedPlatform.value
                )
                HomeContent(
                    games = games,
                    state = listState,
                    modifier = modifier,
                    onClick = { guid, title ->
                        navigateToDetail(guid, title)
                    }
                )
            }
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                ),
                modifier = Modifier
                    .padding(bottom = 30.dp, end = 16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                ScrollToTopButton(
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(index = 0)
                        }
                    }
                )
            }
        }
    }

}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    games: LazyPagingItems<ResultsItem>,
    state: LazyListState,
    onClick: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = games.itemCount,
            key = { it }
        ) { index ->
            val game = games[index]
            if (game != null) {
                GameItem(
                    name = game.name,
                    platforms = game.platforms?.joinToString { it.name },
                    image = game.image.mediumUrl,
                    modifier = Modifier.clickable { onClick(game.guid, game.name) }
                )
            }
        }

        games.apply {
            when {
                loadState.refresh is LoadState.Loading -> { // first load
                    item { PaginationLoading(modifier.fillParentMaxWidth()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = games.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { refresh() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> { // pagination
                    item { PaginationLoading(modifier.fillParentMaxWidth()) }
                }

                loadState.append is LoadState.Error -> {
                    val error = games.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.scroll_to_top)
        )
    }
}
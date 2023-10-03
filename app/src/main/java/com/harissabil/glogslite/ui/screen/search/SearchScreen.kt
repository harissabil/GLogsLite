package com.harissabil.glogslite.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harissabil.glogslite.R
import com.harissabil.glogslite.data.remote.response.ResultsItem
import com.harissabil.glogslite.di.Injection
import com.harissabil.glogslite.ui.ViewModelFactory
import com.harissabil.glogslite.ui.common.UiState
import com.harissabil.glogslite.ui.components.ErrorMessage
import com.harissabil.glogslite.ui.components.GameItem
import com.harissabil.glogslite.ui.components.PaginationLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    query: String,
    navigateBack: () -> Unit,
    viewModel: SearchViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (String, String) -> Unit
) {
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Result", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        viewModel.uiState.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    PaginationLoading(
                        modifier = modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                    viewModel.getSearchGame(query)
                }

                is UiState.Success -> {
                    if (uiState.data.results.isEmpty()) {
                        ErrorMessage(
                            modifier = modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            message = "No Result",
                            onClickRetry = {
                                viewModel.getSearchGame(query)
                            }
                        )
                    } else {
                        SearchContent(
                            games = uiState.data.results,
                            state = listState,
                            modifier = modifier.padding(innerPadding),
                            onClick = { guid, name ->
                                navigateToDetail(guid, name)
                            }
                        )
                    }
                }

                is UiState.Error -> {
                    ErrorMessage(
                        modifier = modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        message = uiState.errorMessage,
                        onClickRetry = {
                            viewModel.getSearchGame(query)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    games: List<ResultsItem>,
    state: LazyListState,
    onClick: (String, String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = games.size,
            key = { it }
        ) { index ->
            val game = games[index]
            GameItem(
                name = game.name,
                platforms = game.platforms.joinToString { it.name },
                image = game.image.mediumUrl,
                modifier = Modifier.clickable { onClick(game.guid, game.name) }
            )
        }
    }
}
package com.harissabil.glogslite.ui.screen.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harissabil.glogslite.R
import com.harissabil.glogslite.data.local.entity.GameEntity
import com.harissabil.glogslite.di.Injection
import com.harissabil.glogslite.ui.ViewModelFactory
import com.harissabil.glogslite.ui.common.UiState
import com.harissabil.glogslite.ui.components.GameItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (String, String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.library),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        val state = rememberLazyListState()
        viewModel.uiState.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllLibrary()
                }

                is UiState.Success -> {
                    if (uiState.data.isEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                        ) {
                            Text(
                                text = stringResource(R.string.no_games_in_library),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        LibraryContent(
                            modifier = modifier.padding(innerPadding),
                            games = uiState.data,
                            state = state,
                            onClick = navigateToDetail
                        )
                    }
                }

                is UiState.Error -> {

                }
            }
        }
    }
}

@Composable
fun LibraryContent(
    modifier: Modifier = Modifier,
    games: List<GameEntity>,
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
            key = { index -> games[index].id }
        ) { index ->
            val game = games[index]
            GameItem(
                name = game.title,
                platforms = game.platform,
                image = game.image,
                modifier = Modifier.clickable { onClick(game.guid, game.title) }
            )
        }
    }
}
package com.harissabil.glogslite.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.harissabil.glogslite.R
import com.harissabil.glogslite.data.local.entity.GameEntity
import com.harissabil.glogslite.data.remote.response.Results
import com.harissabil.glogslite.di.Injection
import com.harissabil.glogslite.ui.ViewModelFactory
import com.harissabil.glogslite.ui.common.UiState
import com.harissabil.glogslite.ui.components.ErrorMessage
import com.harissabil.glogslite.ui.components.PaginationLoading
import com.harissabil.glogslite.ui.components.PlatformChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    gameId: String,
    title: String,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
) {
    var id by remember {
        mutableIntStateOf(0)
    }
    var isAdded by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
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
    }) { innerPadding ->
        viewModel.uiState.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    PaginationLoading(
                        modifier = modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                    viewModel.getDetailGame(gameId)
                }

                is UiState.Success -> {
                    viewModel.addedGame.collectAsState().value.let { game ->
                        when (game) {
                            is UiState.Success -> {
                                if (game.data.guid.isNotEmpty()) {
                                    isAdded = game.data.guid == gameId
                                    id = game.data.id
                                } else {
                                    isAdded = false
                                }
                            }

                            else -> {
                                viewModel.isAdded(gameId)
                            }
                        }
                        DetailContent(
                            detail = uiState.data.results,
                            modifier = Modifier.padding(innerPadding),
                            addToLibrary = {
                                if (isAdded) {
                                    viewModel.delete(
                                        GameEntity(
                                            id = id,
                                            title = uiState.data.results.name,
                                            image = uiState.data.results.image.mediumUrl,
                                            guid = uiState.data.results.guid,
                                            platform = uiState.data.results.platforms?.joinToString { it.name })
                                    )
                                    isAdded = false
                                } else {
                                    viewModel.insert(
                                        GameEntity(title = uiState.data.results.name,
                                            image = uiState.data.results.image.mediumUrl,
                                            guid = uiState.data.results.guid,
                                            platform = uiState.data.results.platforms?.joinToString { it.name })
                                    )
                                    isAdded = true
                                }
                            },
                            added = isAdded,
                        )
                    }
                }

                is UiState.Error -> {
                    ErrorMessage(modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                        message = uiState.errorMessage,
                        onClickRetry = {
                            viewModel.getDetailGame(gameId)
                        })
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    addToLibrary: () -> Unit,
    added: Boolean,
    detail: Results
) {
    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
    ) {
        AsyncImage(
            model = detail.image.originalUrl,
            contentDescription = detail.name,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        if (detail.platforms != null) {
            PlatformChip(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                itemList = detail.platforms.map { it.name },
                selectedItem = "",

                )
        } else {
            Text(
                text = stringResource(R.string.unknown),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Start
            )
        }
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = detail.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = addToLibrary) {
                Icon(
                    imageVector = if (!added) Icons.Outlined.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = null
                )
            }
        }
        Card(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        ) {
            Text(
                text = detail.deck,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
    }
}
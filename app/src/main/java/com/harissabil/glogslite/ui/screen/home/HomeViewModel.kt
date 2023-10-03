package com.harissabil.glogslite.ui.screen.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.data.remote.response.ResultsItem
import com.harissabil.glogslite.helper.Platforms.Companion.getGameId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(repository: GameRepository) : ViewModel() {

    private val _platform: MutableStateFlow<String> = MutableStateFlow("PlayStation 4")
    val platform: MutableStateFlow<String>
        get() = _platform

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
    }

    val games: Flow<PagingData<ResultsItem>> = _platform.flatMapLatest { platform ->
        repository.getGames(getGameId(platform)!!)
    }.cachedIn(viewModelScope)

    fun setPlatform(platform: String) {
        _platform.value = platform
        Log.d("HomeViewModel", "setPlatform: ${this._platform.value}")
    }
}
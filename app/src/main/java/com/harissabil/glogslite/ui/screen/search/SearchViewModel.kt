package com.harissabil.glogslite.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.data.remote.response.SearchResponse
import com.harissabil.glogslite.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: GameRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<SearchResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<SearchResponse>>
        get() = _uiState

    fun getSearchGame(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.getSearchGame(query).collect { searchResponse ->
                    _uiState.value = UiState.Success(searchResponse)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}
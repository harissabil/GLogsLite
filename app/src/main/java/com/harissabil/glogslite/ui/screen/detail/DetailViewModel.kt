package com.harissabil.glogslite.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.data.local.entity.GameEntity
import com.harissabil.glogslite.data.remote.response.DetailResponse
import com.harissabil.glogslite.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: GameRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<DetailResponse>>
        get() = _uiState

    fun getDetailGame(guid: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.getDetailGame(guid).collect { detailResponse ->
                    _uiState.value = UiState.Success(detailResponse)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun insert(game: GameEntity) = viewModelScope.launch {
        repository.insert(game)
    }

    fun delete(game: GameEntity) = viewModelScope.launch {
        repository.delete(game)
    }

    private val _addedGame: MutableStateFlow<UiState<GameEntity>> =
        MutableStateFlow(UiState.Loading)

    val addedGame: StateFlow<UiState<GameEntity>>
        get() = _addedGame

    fun isAdded(guid: String) {
        viewModelScope.launch {
            _addedGame.value = UiState.Loading
            try {
                repository.getAddedGameByGuid(guid).collectLatest { game ->
                    _addedGame.value = UiState.Success(game)
                }
            } catch (e: Exception) {
                _addedGame.value = UiState.Error(e.message.toString())
            }
        }
    }
}
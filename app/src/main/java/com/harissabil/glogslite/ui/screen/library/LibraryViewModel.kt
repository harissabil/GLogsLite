package com.harissabil.glogslite.ui.screen.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.data.local.entity.GameEntity
import com.harissabil.glogslite.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(private val repository: GameRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<GameEntity>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GameEntity>>>
        get() = _uiState

    fun getAllLibrary() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.getAllLibrary().collect { library ->
                    _uiState.value = UiState.Success(library)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}
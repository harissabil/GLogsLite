package com.harissabil.glogslite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.ui.screen.detail.DetailViewModel
import com.harissabil.glogslite.ui.screen.home.HomeViewModel
import com.harissabil.glogslite.ui.screen.library.LibraryViewModel
import com.harissabil.glogslite.ui.screen.search.SearchViewModel

class ViewModelFactory(private val repository: GameRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            return LibraryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
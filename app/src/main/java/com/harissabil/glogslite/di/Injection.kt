package com.harissabil.glogslite.di

import android.content.Context
import com.harissabil.glogslite.data.GameRepository
import com.harissabil.glogslite.data.local.room.GameDatabase
import com.harissabil.glogslite.data.remote.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): GameRepository {
        val apiService = ApiConfig.getApiService()
        val database = GameDatabase.getDatabase(context)
        val dao = database.gameDao()
        return GameRepository.getInstance(apiService, dao)
    }
}
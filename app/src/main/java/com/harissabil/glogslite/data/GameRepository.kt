package com.harissabil.glogslite.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.harissabil.glogslite.BuildConfig
import com.harissabil.glogslite.data.local.entity.GameEntity
import com.harissabil.glogslite.data.local.room.GameDao
import com.harissabil.glogslite.data.remote.api.ApiService
import com.harissabil.glogslite.data.remote.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GameRepository private constructor(
    private val apiService: ApiService,
    private val mGameDao: GameDao,
) {
    fun getGames(platform: Int) = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
        ),
        pagingSourceFactory = {
            GamePagingSource(
                platform = platform,
                apiService = apiService
            )
        }
    ).flow

    suspend fun getSearchGame(query: String): Flow<SearchResponse> {
        return flowOf(apiService.getSearchGame(BuildConfig.API_KEY, "json", query))
    }

    suspend fun getDetailGame(guid: String) =
        flowOf(apiService.getDetailGame(guid, BuildConfig.API_KEY, "json"))

    // Database
    suspend fun insert(game: GameEntity) = mGameDao.insert(game)
    suspend fun delete(game: GameEntity) = mGameDao.delete(game)
    fun getAddedGameByGuid(guid: String) = mGameDao.getAddedGameByGuid(guid)
    fun getAllLibrary(): Flow<List<GameEntity>> = mGameDao.getAllLibrary()

    companion object {
        @Volatile
        private var instance: GameRepository? = null
        fun getInstance(
            apiService: ApiService,
            gameDao: GameDao
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(apiService, gameDao)
            }.also { instance = it }
    }
}
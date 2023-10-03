package com.harissabil.glogslite.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harissabil.glogslite.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: GameEntity)

    @Delete
    suspend fun delete(game: GameEntity)

    @Query("SELECT * FROM game WHERE guid = :guid")
    fun getAddedGameByGuid(guid: String): Flow<GameEntity>

    @Query("SELECT * FROM game ORDER BY id DESC")
    fun getAllLibrary(): Flow<List<GameEntity>>
}
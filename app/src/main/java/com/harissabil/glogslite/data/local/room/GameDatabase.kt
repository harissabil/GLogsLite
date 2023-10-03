package com.harissabil.glogslite.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harissabil.glogslite.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): GameDatabase {
            if (INSTANCE == null) {
                synchronized(GameDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GameDatabase::class.java,
                        "game_database"
                    ).build()
                }
            }
            return INSTANCE as GameDatabase
        }
    }
}
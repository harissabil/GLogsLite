package com.harissabil.glogslite.helper

object Platform {
    const val PLAYSTATION_4 = 146
    const val PC = 94
    const val NINTENDO_SWITCH = 157
    const val XBOX_SERIES_XS = 179
}

enum class Platforms(val value: String) {
    PLAYSTATION_4("PlayStation 4"),
    PC("PC"),
    NINTENDO_SWITCH("Nintendo Switch"),
    XBOX_SERIES_XS("Xbox Series XS");

    companion object {
        fun getAllGames(): List<String> {
            return values().map { it.value }
        }

        fun getGameId(value: String): Int? {
            return when (value) {
                PLAYSTATION_4.value -> Platform.PLAYSTATION_4
                PC.value -> Platform.PC
                NINTENDO_SWITCH.value -> Platform.NINTENDO_SWITCH
                XBOX_SERIES_XS.value -> Platform.XBOX_SERIES_XS
                else -> null
            }
        }
    }
}
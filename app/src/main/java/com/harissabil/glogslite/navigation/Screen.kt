package com.harissabil.glogslite.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Library : Screen("library")
    object Profile : Screen("profile")
    object Search : Screen("home/{query}") {
        fun createRoute(query: String) = "home/$query"
    }
    object Detail : Screen("home/{gameId}/{title}") {
        fun createRoute(gameId: String, title: String) = "home/$gameId/${title.replace("/", "")}"
    }
}

package com.harissabil.glogslite

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harissabil.glogslite.navigation.NavigationItem
import com.harissabil.glogslite.navigation.Screen
import com.harissabil.glogslite.ui.screen.detail.DetailScreen
import com.harissabil.glogslite.ui.screen.home.HomeScreen
import com.harissabil.glogslite.ui.screen.library.LibraryScreen
import com.harissabil.glogslite.ui.screen.profile.ProfileScreen
import com.harissabil.glogslite.ui.screen.search.SearchScreen
import com.harissabil.glogslite.ui.theme.GLogsLiteTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GLogsLiteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route && currentRoute != Screen.Search.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                Screen.Home.route,
            ) {
                HomeScreen(
                    navigateToSearch = { query ->
                        navController.navigate(Screen.Search.createRoute(query))
                    },
                    navigateToDetail = { gameId, title ->
                        navController.navigate(Screen.Detail.createRoute(gameId, title))
                    }
                )
            }
            composable(Screen.Library.route) {
                LibraryScreen(
                    navigateToDetail = { gameId, title ->
                        navController.navigate(Screen.Detail.createRoute(gameId, title))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Search.route,
                arguments = listOf(navArgument("query") { type = NavType.StringType }),
            ) {
                val query = it.arguments?.getString("query") ?: ""
                SearchScreen(
                    query = query,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { gameId, title ->
                        navController.navigate(Screen.Detail.createRoute(gameId, title))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("gameId") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType }),
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it / 2 },
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it / 2 },
                        animationSpec = tween(300)
                    )
                }
            ) {
                val gameId = it.arguments?.getString("gameId") ?: ""
                val title = it.arguments?.getString("title") ?: ""
                DetailScreen(
                    gameId = gameId,
                    title = title,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Outlined.Home,
                iconPressed = Icons.Filled.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_library),
                icon = ImageVector.vectorResource(R.drawable.ic_library_outlined),
                iconPressed = ImageVector.vectorResource(R.drawable.ic_library_filled),
                screen = Screen.Library
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Outlined.Person,
                iconPressed = Icons.Filled.Person,
                screen = Screen.Profile
            )
        )
        navigationItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.screen.route) item.iconPressed else item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}


@Preview
@Composable
fun GLogsLiteAppPreview() {
    GLogsLiteTheme {
        GLogsLiteApp()
    }
}
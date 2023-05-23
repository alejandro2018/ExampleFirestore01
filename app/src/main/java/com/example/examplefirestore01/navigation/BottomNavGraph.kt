package com.example.example01.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.example01.home.HomeView
import com.example.example01.setting.SettingsScreen
import com.example.example01.testsone.TestOne
import com.example.example01.testsone.TestTwo

@Composable
fun BottomNavGraph(showAd: () -> Unit, navController: NavHostController) {

    var adCount by rememberSaveable { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination =  BottomBarScreen.Inicio.route
    ) {

        composable(route = BottomBarScreen.Inicio.route) {
            HomeView(navController)
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingsScreen(navController)
        }
        composable(route = Routes.TestOne.route) {
            TestOne(showAd, navController)
        }
        composable(route = Routes.TestTwo.route) {
            TestTwo(navController)
        }



    }
}
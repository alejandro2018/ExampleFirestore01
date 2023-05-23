package com.example.example01.navigation

sealed class Routes(val route:String) {

    object HomeView:Routes("HomeView")
    object TestOne:Routes("TestOne")
    object TestTwo:Routes("TestTwo")
    object SettingsScreen:Routes("SettingsScreen")

}
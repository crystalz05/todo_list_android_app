package com.tyro.todolistapp.screens

sealed class Screen(val route: String) {

    object HomeScreen: Screen("home_screen")
    object AddUpdateScreen: Screen("add_update_screen")

}
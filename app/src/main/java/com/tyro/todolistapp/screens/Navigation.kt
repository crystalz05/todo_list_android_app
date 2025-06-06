package com.tyro.todolistapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tyro.todolistapp.viewModel.TodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(viewModel: TodoViewModel = viewModel<TodoViewModel>(),
               navHostController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route,

        enterTransition = { fadeIn(animationSpec = tween(100)) },
        exitTransition = { fadeOut(animationSpec = tween(100)) },
        popEnterTransition = { fadeIn(animationSpec = tween(100)) },
        popExitTransition = { fadeOut(animationSpec = tween(100)) },

//        enterTransition = {slideIntoContainer(towards = AnimatedContentTransitionScope
//            .SlideDirection.Left)},
//        exitTransition = {slideOutOfContainer(towards = AnimatedContentTransitionScope
//            .SlideDirection.Left)},
//        popEnterTransition = {slideIntoContainer(towards = AnimatedContentTransitionScope
//            .SlideDirection.Right)},
//        popExitTransition = {slideOutOfContainer(towards = AnimatedContentTransitionScope
//            .SlideDirection.Right)}
        ) {

        composable(Screen.HomeScreen.route) {
            TodoListScreen(navHostController, viewModel)
        }

        composable(Screen.AddUpdateScreen.route + "/{todoId}",
            arguments = listOf(navArgument("todoId"){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })

            ) {backStackEntry ->
            val todoId = backStackEntry.arguments?.getLong("todoId") ?: 0L
            AddUpdateTodo(viewModel, navHostController, todoId)
        }

    }





}
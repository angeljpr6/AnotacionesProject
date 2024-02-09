package com.example.anotacionesproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.anotacionesproject.viewModel.BlogNotesViewModel
import com.example.anotacionesproject.viewModel.NotesViewModel
import com.example.anotacionesproject.views.AddView
import com.example.anotacionesproject.views.EditView
import com.example.anotacionesproject.views.HomeView

@Composable
fun NavManager(notesViewModel: NotesViewModel, blogNotesViewModel: BlogNotesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, notesViewModel)

        }

        composable("AddView"){
            AddView(navController, notesViewModel, blogNotesViewModel)
        }

        composable("EditView/{id}", arguments = listOf(navArgument("id"){type= NavType.LongType})){
            val id=it.arguments?.getLong("id")?:0
            EditView(navController, blogNotesViewModel, notesViewModel, id)
        }


    }
}



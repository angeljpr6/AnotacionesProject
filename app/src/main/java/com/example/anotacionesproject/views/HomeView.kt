package com.example.anotacionesproject.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.anotacionesproject.viewModel.NotesViewModel
import com.example.blognotas.components.CronCard
import com.example.blognotas.components.FloatButton
import com.example.blognotas.components.MainTitle
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, notesViewModel: NotesViewModel) {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "Notas") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )   )
        },
        floatingActionButton = {

            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, notesViewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, notesViewModel: NotesViewModel){
    var buscar by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(it)
            .padding(top = 10.dp)
    ){

        OutlinedTextField(
            value = buscar,
            onValueChange = { buscar = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            label = { Text("Buscar") },
            leadingIcon = {
                // Icono de búsqueda
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
            }

        )


        val notesList = notesViewModel.notesList.collectAsState()
            .value.filter { item ->
                item.title.contains(buscar, ignoreCase = true)
            }


        LazyColumn {
            items(notesList){item ->

                val delete= SwipeAction(
                    icon= rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {notesViewModel.deleteNote(item)}
                )
                SwipeableActionsBox(endActions = listOf(delete),
                    swipeThreshold = 270.dp) {
                    CronCard(item.title, item.body,){
                        navController.navigate("EditView/${item.id}")
                    }
                }


            }

        }
    }
}



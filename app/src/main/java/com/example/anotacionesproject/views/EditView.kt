package com.example.anotacionesproject.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.anotacionesproject.model.Notes
import com.example.anotacionesproject.viewModel.BlogNotesViewModel
import com.example.anotacionesproject.viewModel.NotesViewModel
import com.example.blognotas.components.MainIconButton
import com.example.blognotas.components.MainTextField
import com.example.blognotas.components.MainTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(navController: NavController, blogNotesViewModel: BlogNotesViewModel, notesViewModel: NotesViewModel, id: Long
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "Añadir Nota") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                ),
                navigationIcon ={
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()

                    }
                }
            )
        }

    ) {
        ContentEditView(it, navController, blogNotesViewModel, notesViewModel , id)
    }
}


@Composable
fun ContentEditView(it: PaddingValues, navController: NavController, blogNotesViewModel: BlogNotesViewModel, notesViewModel: NotesViewModel, id: Long){

    LaunchedEffect(Unit){
        blogNotesViewModel.getNotaById(id)
    }

    val state= blogNotesViewModel.state

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical=16.dp)
        ) {

        }
        MainTextField(value =state.title ,
            onValueChange = {blogNotesViewModel.onValueTitulo(it)},
            label ="Title"
        )

        MainTextField(value =state.body ,
            onValueChange = {blogNotesViewModel.onValueDescription(it)},
            label ="Description"
        )

        Row(
            Modifier.padding(2.dp)

        ) {

            Button(
                onClick = {
                notesViewModel.updateNote(
                    Notes(
                        id=id,
                        title=state.title,
                        body = state.body,
                    )
                )

                navController.popBackStack()
            }) {
                Text(text = "Editar")

            }

            Button(onClick = {
                notesViewModel.deleteNote(
                    Notes(
                        id=id,
                        title=state.title,
                        body = state.body,
                    )
                )

                navController.popBackStack()
            }) {
                Text(text = "Borrar")
            }
        }
    }
}
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun AddView(
    navController: NavController,
    notesViewModel: NotesViewModel,
    blogNotesViewModel: BlogNotesViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "Nueva nota") },
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
        ContentAddView(it, navController, notesViewModel, blogNotesViewModel)
    }
}

@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, notesViewModel: NotesViewModel, blogNotasVM: BlogNotesViewModel ){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.run {
            padding(it)
                .padding(top = 30.dp)
                .fillMaxSize()
        },
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        MainTextField(
            value = title,
            onValueChange =  {title = it},
            label = "Title"
        )

        MainTextField(
            value = description,
            onValueChange = {description = it},
            label = "Description"
        )

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical=16.dp)

        ){
            Button(onClick = {
                notesViewModel.addNote(
                    Notes(
                        title = title,
                        body = description
                    )
                )
                navController.popBackStack()
            }) {
                Text(text = "Guardar")

            }
        }

    }
}
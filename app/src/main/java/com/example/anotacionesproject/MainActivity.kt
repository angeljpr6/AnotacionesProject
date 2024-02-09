package com.example.anotacionesproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.anotacionesproject.navigation.NavManager
import com.example.anotacionesproject.ui.theme.AnotacionesProjectTheme
import com.example.anotacionesproject.viewModel.BlogNotesViewModel
import com.example.anotacionesproject.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesViewModel: NotesViewModel by viewModels()
        val blogNotesViewModel : BlogNotesViewModel by viewModels()
        setContent {
            AnotacionesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(notesViewModel, blogNotesViewModel)
                }
            }
        }
    }
}

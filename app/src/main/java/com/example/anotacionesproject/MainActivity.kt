package com.example.anotacionesproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.anotacionesproject.navigation.NavManager
import com.example.anotacionesproject.ui.theme.AnotacionesProjectTheme
import com.example.anotacionesproject.viewModel.BlogNotesViewModel
import com.example.anotacionesproject.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notasVM: NotesViewModel by viewModels()
        val blogNotasVM : BlogNotesViewModel by viewModels()
        setContent {
            AnotacionesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(notasVM, blogNotasVM)
                }
            }
        }
    }
}

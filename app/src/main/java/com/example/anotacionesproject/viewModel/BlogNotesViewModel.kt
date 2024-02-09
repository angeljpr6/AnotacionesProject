package com.example.anotacionesproject.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotacionesproject.repository.NotesRepository
import com.example.anotacionesproject.state.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogNotesViewModel  @Inject constructor (private val repository: NotesRepository): ViewModel(){
    var state by mutableStateOf(NotesState(title = "", body = ""))
        private set

    fun getNotaById(id:Long){
        viewModelScope.launch (Dispatchers.IO) {
            repository.getNoteById(id).collect { item ->
                if (item != null) {
                    state = state.copy(title = item.title, body = item.body)
                } else {
                }
            }
        }
    }

    fun onValueTitulo(value : String){
        state = state.copy(title = value)
    }

    fun onValueDescription(value : String){
        state = state.copy(body = value)
    }
}
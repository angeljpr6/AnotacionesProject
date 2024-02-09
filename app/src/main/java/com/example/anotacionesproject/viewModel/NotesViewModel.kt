package com.example.anotacionesproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotacionesproject.model.Notes
import com.example.anotacionesproject.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NotesRepository): ViewModel() {
    private val _notesList = MutableStateFlow<List<Notes>>(emptyList())
    val notesList=_notesList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllNotes().collect{
                    item->
                if (item.isNullOrEmpty()){
                    _notesList.value= emptyList()
                } else {
                    _notesList.value=item
                }
            }
        }
    }
    fun addNote (note:Notes)=viewModelScope.launch{repository.addNote(note)}
    fun updateNote (note:Notes)=viewModelScope.launch{repository.updateNote(note)}
    fun deleteNote (note: Notes)=viewModelScope.launch{repository.deleteNote(note)}

}
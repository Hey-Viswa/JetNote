package com.binary.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.binary.jetnote.data.NotesDataSource

class NoteViewModel : ViewModel() {
    var noteList = mutableStateListOf<Notes>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(notes: Notes) {
        noteList.add(notes)
    }

    fun removeNote(notes: Notes) {
        noteList.remove(notes   )
    }
    fun getAllNotes(): List<Notes>{
        return noteList
    }
}
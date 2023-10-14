package com.binary.jetnote.data

import com.binary.jetnote.screen.Notes

class NotesDataSource {
    fun loadNotes(): List<Notes>{
        return listOf(

            Notes(title = "A good day", description = "We went on a vacation by the lake"),
            Notes(title = "Android Compose", description = "Working on Android Compose course today"),
            Notes(title = "Keep at it...", description = "Sometimes things just happen"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family today"),
            Notes(title = "A movie day", description = "Watching a movie with family")

        )
    }
}
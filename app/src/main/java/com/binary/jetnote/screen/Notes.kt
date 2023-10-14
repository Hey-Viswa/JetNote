package com.binary.jetnote.screen

import java.time.LocalDateTime
import java.util.UUID

data class Notes(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
    )


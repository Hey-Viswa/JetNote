package com.binary.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binary.jetnote.R
import com.binary.jetnote.components.NoteInputText
import com.binary.jetnote.data.NotesDataSource
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NoteScreen(
    notes: List<Notes>,
    onAddNote: (Notes) -> Unit,
    onRemoveNote: (Notes) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context  = LocalContext.current
    Column {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon",
                tint = Color(0xff031d44)
            )
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF2ec4b6)
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )
            Spacer(
                modifier = Modifier.padding(5.dp)
            )
            NoteInputText(
                text = description,
                label = "Description",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            NoteButton(
                text = "Save",
                onClick = {

                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        //save//add to list
                        onAddNote(Notes(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }

                }
            )
        }
        Divider(modifier = Modifier.padding((4.dp)))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClick = {
                    onRemoveNote(note)
                })
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFff9f1c),
            contentColor = Color(0xff031d44)
        )
    ) {
        Text(text)
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Notes,
    onNoteClick: (Notes) -> Unit
) {
    Surface(
        modifier
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(topEnd = 20.dp, bottomStart = 20.dp, topStart = 20.dp))
            .fillMaxWidth(),
        color = Color(0xFFffbf69),
        shadowElevation = 6.dp,
        tonalElevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xff031d44)
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}

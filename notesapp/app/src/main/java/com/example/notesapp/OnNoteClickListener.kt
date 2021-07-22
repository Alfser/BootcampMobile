package com.example.notesapp

import android.database.Cursor

interface OnNoteClickListener {
    fun itemClicked(cursor: Cursor)
}
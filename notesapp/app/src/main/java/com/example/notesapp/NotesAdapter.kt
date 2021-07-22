package com.example.notesapp

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import com.example.notesapp.databinding.RecyclerViewItemBinding

class NotesAdapter(private val itemClicked: (cursor: Cursor) -> Unit) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var cursor: Cursor? = null

    inner class NotesViewHolder(val binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder{
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        cursor?.moveToPosition(position)

        with(holder){
            binding.textViewTitle.text = cursor?.getString(cursor?.getColumnIndex(TITLE_NOTES) as Int)
            itemView.setOnClickListener{itemClicked(cursor as Cursor)}
        }

    }

    override fun getItemCount(): Int = cursor?.count ?: 0

    fun setCursor(cursor: Cursor?){
        this.cursor = cursor
        notifyDataSetChanged()
    }

}


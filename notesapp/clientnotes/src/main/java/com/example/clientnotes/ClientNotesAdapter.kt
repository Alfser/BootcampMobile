package com.example.clientnotes

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clientnotes.databinding.RecyclerViewItemBinding

class ClientNotesAdapter(private val cursor: Cursor): RecyclerView.Adapter<ClientNotesAdapter.ClientNotesViewHolder>() {

    inner class ClientNotesViewHolder(val binding:RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientNotesViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClientNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientNotesViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.binding.clientItemTitle.text = cursor.getString(cursor.getColumnIndex("title"))
        holder.binding.clientItemDescription.text = cursor.getString(cursor.getColumnIndex("description"))

    }

    override fun getItemCount() = cursor.count
}
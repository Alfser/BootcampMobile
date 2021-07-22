package com.example.clientnotes

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clientnotes.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clientButtonRefresh.setOnClickListener { getContentProvider() }
    }

    private fun getContentProvider(){
        try {
            val url = "content://com.example.notesapp.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data, null, null, null, "title")
            binding.recyclerMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientNotesAdapter(cursor as Cursor)
            }
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }
}
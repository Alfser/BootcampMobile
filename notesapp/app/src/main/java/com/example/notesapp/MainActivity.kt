package com.example.notesapp

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns._ID
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.database.NotesContentProvider.Companion.BASE_URI
import com.example.notesapp.database.NotesContentProvider.Companion.URI_NOTES
import com.example.notesapp.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import com.example.notesapp.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var binding: ActivityMainBinding
    lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabNotes.setOnClickListener {
            NotesDetailsFragment().show(supportFragmentManager, "dialog")
        }

        notesAdapter = NotesAdapter{cursor ->
            val id = cursor.getLong(cursor.getColumnIndex(_ID))
            val fragment = NotesDetailsFragment.newInstance(id)
            fragment.show(supportFragmentManager, "dialog")
        }
        
        notesAdapter.setHasStableIds(true)
        binding.recycleViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recycleViewNotes.adapter = notesAdapter

        //Initializing loaderManager to run contentProviders in background
        LoaderManager.getInstance(this).initLoader(0, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(this, URI_NOTES, null, null, null, TITLE_NOTES)

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if(data != null){
            notesAdapter.setCursor(data)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        notesAdapter.setCursor(null)
    }
}
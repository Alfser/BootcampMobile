package com.example.notesapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.notesapp.database.NotesContentProvider.Companion.URI_NOTES
import com.example.notesapp.database.NotesDatabaseHelper.Companion.DESCRIPTION_NOTES
import com.example.notesapp.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import com.example.notesapp.databinding.NotesDetailsBinding

class NotesDetailsFragment : DialogFragment(), DialogInterface.OnClickListener {

    private lateinit var binding: NotesDetailsBinding
    private var id: Long = 0

    companion object{
        private const val EXTRA_ID = "id"

        fun newInstance(id: Long): NotesDetailsFragment{
            val bundle = Bundle()
            bundle.putLong(EXTRA_ID, id)

            val notesDetailsFragment = NotesDetailsFragment()
            notesDetailsFragment.arguments = bundle
            return notesDetailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = NotesDetailsBinding.inflate(requireActivity().layoutInflater)

        var newNotes = true

        if(arguments != null && arguments?.getLong(EXTRA_ID) != 0L){
            id = requireArguments().getLong(EXTRA_ID)
            val uri = Uri.withAppendedPath(URI_NOTES, id.toString())
            val cursor =
                requireActivity().contentResolver.query(uri, null, null, null, null)

            if(cursor?.moveToNext() as Boolean){
                newNotes = false

                binding.edtTxtTitle.setText(cursor.getString(cursor.getColumnIndex(TITLE_NOTES)))
                binding.edtTxtDescription.setText(cursor.getString(cursor.getColumnIndex(DESCRIPTION_NOTES)))
            }

            cursor.close()
        }

        return AlertDialog.Builder(requireActivity())
            .setTitle(if(newNotes) "Nova Tarefa" else "Editar Tarefa")
            .setView(binding.root)
            .setPositiveButton("Salvar", this)
            .setNegativeButton("Cancelar", this)
            .create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        val values = ContentValues()
        values.put(TITLE_NOTES, binding.edtTxtTitle.text.toString())
        values.put(DESCRIPTION_NOTES, binding.edtTxtDescription.text.toString())

        if(id != 0L){
            val uri = Uri.withAppendedPath(URI_NOTES, id.toString())
            requireContext().contentResolver.update(uri, values, null, null)
        }
        else{
            requireContext().contentResolver.insert(URI_NOTES, values)
            requireContext().showNotification("1234", "Notes App", "Notes created.")
        }
    }
}
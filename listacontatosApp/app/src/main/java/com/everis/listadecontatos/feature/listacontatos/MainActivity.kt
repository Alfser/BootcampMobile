package com.everis.listadecontatos.feature.listacontatos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.listadecontatos.R
import com.everis.listadecontatos.application.ContactsApplication
import com.everis.listadecontatos.bases.BaseActivity
import com.everis.listadecontatos.feature.contato.ContactActivity
import com.everis.listadecontatos.feature.listacontatos.adapter.ContatoAdapter
import com.everis.listadecontatos.feature.listacontatos.model.ContactsModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : BaseActivity() {

    private var adapter:ContatoAdapter? = null
    private val sleepTime: Long = 500 //thread sleep time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolBar(toolBar, "Lista de contatos",false)
        setupListView()
        setupOnClicks()
    }

    private fun setupOnClicks(){
        fab.setOnClickListener { onClickAdd() }
        ivBuscar.setOnClickListener {
            if(!etBuscar.isEnabled()){
                etBuscar.setEnabled(true)
                return@setOnClickListener
            }
            onClickBuscar()
        }
    }

    private fun setupListView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        //buscar contatos
        onClickBuscar()
    }

    private fun onClickAdd(){
        val intent = Intent(this,ContactActivity::class.java)
        startActivity(intent)
    }

    private fun onClickItemRecyclerView(id: Int){
        val intent = Intent(this,ContactActivity::class.java)
        intent.putExtra("index", id)
        startActivity(intent)
    }

    private fun onClickBuscar(){

        progress.visibility = View.VISIBLE
        Thread {
            Thread.sleep(sleepTime)

            val stringSearch = etBuscar.text.toString()
            var FilteredList = mutableListOf<ContactsModel>()
            FilteredList = ContactsApplication.instance.helperDB?.queryContacts(stringSearch)
                ?: mutableListOf()

            runOnUiThread {
                adapter =
                    ContatoAdapter(this@MainActivity, FilteredList) { onClickItemRecyclerView(it) }
                recyclerView.adapter = adapter
                progress.visibility = View.GONE
            }
        }.start()
    }
}

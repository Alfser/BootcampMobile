package com.everis.listadecontatos.feature.contato

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.everis.listadecontatos.R
import com.everis.listadecontatos.application.ContactsApplication
import com.everis.listadecontatos.bases.BaseActivity
import com.everis.listadecontatos.feature.listacontatos.model.ContactsModel
import kotlinx.android.synthetic.main.activity_contato.*

class ContactActivity : BaseActivity() {

    private var contactId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)


        setupToolBar(toolBar, "Contato",true)
        setupContact()
        btnSalvarConato.setOnClickListener { onClickSalvarContato() }
    }

    private fun setupContact(){
        contactId = intent.getIntExtra("index",-1)
        if (contactId == -1){
            btnExcluirContato.visibility = View.GONE
            return
        }

        progress.visibility = View.VISIBLE
        Thread{
            Thread.sleep(1500)
            val sqlHelper = ContactsApplication.instance.helperDB

            if (sqlHelper !== null){
                val contact: ContactsModel = sqlHelper.queryContactById(contactId)

                runOnUiThread{
                    edNome.setText(contact.name)
                    edTelefone.setText(contact.cellphone)
                    progress.visibility = View.GONE
                }
            }
        }.start()
    }

    private fun onClickSalvarContato(){

        progress.visibility = View.VISIBLE

        Thread{
            Thread.sleep(1500)
            val name = edNome.text.toString()
            val cellphone = edTelefone.text.toString()
            val contact = ContactsModel(
                contactId,
                name,
                cellphone
            )

            //Add Contact to database
            if(contactId == -1){
                ContactsApplication.instance.helperDB?.saveContact(contact)

                runOnUiThread{
                    progress.visibility = View.GONE
                    Toast.makeText(this, "Contato adicionado $name", Toast.LENGTH_SHORT).show()
                    finish()
                }

            //Update contact on database
            }else{
                ContactsApplication.instance.helperDB?.updateContact(contact)

                runOnUiThread{
                    progress.visibility = View.GONE
                    Toast.makeText(this, "Contato $name atualizado", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    fun onClickExcluirContato(view: View) {
        if(contactId>-1){

            val dialog = AlertDialog.Builder(this, R.style.Theme_MaterialComponents_Light_Dialog_Alert)
                .apply {
                    setPositiveButton(
                        "Sim"
                    ) { _, _ ->
                        progress.visibility = View.VISIBLE
                        Thread{
                            Thread.sleep(1500)
                            ContactsApplication.instance.helperDB?.deleteContact(contactId)

                            runOnUiThread{
                                Toast.makeText(
                                    this@ContactActivity,
                                    "contato ${edNome.text} deletado",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progress.visibility = View.GONE
                                finish()
                            }
                        }
                    }
                    setNegativeButton(
                    "Não"
                    ){ _, _ -> {}
                    }
                }.create()

            dialog.setMessage("Deseja excluir o contato ${edNome.text}?")
            dialog.show()

        }
    }
}

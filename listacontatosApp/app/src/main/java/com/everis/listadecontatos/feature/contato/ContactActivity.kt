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
    private val sleepTime: Long = 500 //Time in sleep thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)


        setupToolBar(toolBar, "Contato",true)
        setupContact()

        btnSalvarConato.setOnClickListener {
            if(!edNome.isEnabled){
                edNome.setEnabled(true)
                edTelefone.setEnabled(true)
                edEmail.setEnabled(true)
                btnSalvarConato.text = "Salvar alteração"
                return@setOnClickListener
            }
            onClickSalvarContato()
        }
    }

    private fun setupContact(){
        contactId = intent.getIntExtra("index",-1)
        if (contactId == -1){
            btnExcluirContato.visibility = View.GONE
            edNome.setEnabled(true)
            edTelefone.setEnabled(true)
            edEmail.setEnabled(true)
            return
        }

        btnSalvarConato.text = "Editar Contato"

        progress.visibility = View.VISIBLE
        Thread{
            Thread.sleep(sleepTime)

            val sqlHelper = ContactsApplication.instance.helperDB

            if (sqlHelper !== null){
                val contact: ContactsModel = sqlHelper.queryContactById(contactId)

                runOnUiThread{
                    edNome.setText(contact.name)
                    edTelefone.setText(contact.cellphone)
                    edEmail.setText(contact.email)

                    progress.visibility = View.GONE
                }
            }
        }.start()
    }

    private fun onClickSalvarContato(){

        progress.visibility = View.VISIBLE

        Thread{
            Thread.sleep(sleepTime)
            val name = edNome.text.toString()
            val cellphone = edTelefone.text.toString()
            val email = edEmail.text.toString()
            val contact = ContactsModel(
                contactId,
                name,
                cellphone,
                email
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
                    edNome.setEnabled(false)
                    edTelefone.setEnabled(false)
                    edEmail.setEnabled(false)
                    btnSalvarConato.text = "Editar Contato"
                    Toast.makeText(this, "Contato $name atualizado", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    fun onClickExcluirContato(view: View) {
        if(contactId>-1){

            val dialog = AlertDialog.Builder(this, R.style.Base_ThemeOverlay_AppCompat_Dialog)
                .apply {
                    setPositiveButton(
                        "Sim"
                    ) { _, _ ->
                        progress.visibility = View.VISIBLE
                        Thread{
                            Thread.sleep(sleepTime)
                            ContactsApplication.instance.helperDB?.deleteContact(contactId)

                            runOnUiThread{
                                progress.visibility = View.GONE
                                Toast.makeText(
                                    this@ContactActivity,
                                    "contato ${edNome.text} deletado",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        }.start()
                    }
                    setNegativeButton(
                    "Não"
                    ){ _, _ -> {}
                    }
                }.create()

            dialog.setMessage("Deseja excluir o contato ${edNome.text} ?")
            dialog.show()

        }
    }
}

package com.everis.listadecontatos.feature.listacontatos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everis.listadecontatos.R
import com.everis.listadecontatos.feature.listacontatos.model.ContactsModel
import kotlinx.android.synthetic.main.item_contato.view.*

class ContatoAdapter(
    private val context: Context,
    private val contactList: List<ContactsModel>,
    private val onClick: ((Int) -> Unit)
) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contato,parent,false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        with(holder.itemView){
            tvNome.text = contact.name
            tvTelefone.text = contact.cellphone
            llItem.setOnClickListener { onClick(contact.id) }
        }
    }

}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
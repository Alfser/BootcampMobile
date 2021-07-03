package com.everis.listadecontatos.singleton

import com.everis.listadecontatos.feature.listacontatos.model.ContactsModel

object ContatoSingleton {
    var lista: MutableList<ContactsModel> = mutableListOf()
}
package com.everis.listadecontatos.application

import android.app.Application
import com.everis.listadecontatos.helpers.HelperDB

class ContactsApplication : Application() {

    var helperDB:HelperDB? = null
        private set

    companion object{
        lateinit var instance: ContactsApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }
}
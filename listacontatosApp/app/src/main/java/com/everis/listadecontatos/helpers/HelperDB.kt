package com.everis.listadecontatos.helpers

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.everis.listadecontatos.feature.listacontatos.model.ContactsModel

class HelperDB(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, CURRENT_VERSION) {

    companion object{
        private val DATABASE_NAME = "contacts.db"
        private val CURRENT_VERSION = 1
    }

    val TABLE_NAME = "contact"
    val COLUMN_ID = "id"
    val COLUMN_NAME = "name"
    val COLUMN_CELLPHONE = "cellphone"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER NOT NULL, 
            $COLUMN_NAME TEXT NOT NULL, 
            $COLUMN_CELLPHONE TEXT NOT NULL,
            PRIMARY KEY($COLUMN_ID AUTOINCREMENT)) """.trimIndent()

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    fun queryContacts(queryString: String): MutableList<ContactsModel>{

        val db:SQLiteDatabase = readableDatabase
        val contactsList:MutableList<ContactsModel> = mutableListOf()
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME LIKE ?"
        val queryStringLike: Array<String> = arrayOf("%$queryString%")

        try{
            val cursor = db.rawQuery(sql, queryStringLike)

            if (cursor == null){
                return mutableListOf()
            }

            while(cursor.moveToNext()){

                val contact = ContactsModel(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE))
                )

                contactsList.add(contact)
            }

        }catch (ex: SQLiteException){
            ex.printStackTrace()
        }

        return contactsList
    }

    fun queryContactById(contactId: Int): ContactsModel{

        val db:SQLiteDatabase = readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val params: Array<String> = arrayOf(contactId.toString())

        lateinit var contact: ContactsModel
        val cursor = db.rawQuery(sql, params)

        if (cursor !== null && cursor.moveToFirst()) {

            contact = ContactsModel(
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                cellphone = cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE))
            )

            return  contact
        }

        contact = ContactsModel()
        Log.i("HELPERS_DB", "ID ${contact.id} Selected")
        return contact
    }

    fun saveContact(contact:ContactsModel){
        val db = writableDatabase
        val sql ="INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_CELLPHONE)VALUES(?, ?)"
        val params:Array<String> = arrayOf(contact.name, contact.cellphone)
        db.execSQL(sql, params)
    }

    fun deleteContact(id: Int){
        val db = writableDatabase
        val sql = "DELETE FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val param:Array<String> = arrayOf(id.toString())
        db.execSQL(sql, param)
    }

    fun updateContact(contact:ContactsModel){
        val db = writableDatabase
        val sql = "UPDATE $TABLE_NAME SET $COLUMN_NAME = ?, $COLUMN_CELLPHONE = ? WHERE $COLUMN_ID = ?"
        val params: Array<String> = arrayOf(contact.name, contact.cellphone, contact.id.toString())
        db.execSQL(sql, params)
    }
}
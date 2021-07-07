package com.example.profilescreen

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import java.sql.Date


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_dorwer_layout)
        setupDrawer()

        setSharedPreferences()
    }

    private fun setupDrawer(){
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer)
        toggle.syncState()
    }

    private fun fetchUserDataMock():User{
        val user = User(
            "Foo",
            Date.valueOf("2021-12-10"),
            "foo@gmial.com",
            "path",
            "bar")

        return user
    }

    private fun setSharedPreferences(){
        val user = fetchUserDataMock()

        val preferences = getSharedPreferences("user", MODE_PRIVATE)

        with(preferences.edit()){
            putString(getString(R.string.preference_name), user.name)
            putString(getString(R.string.preference_birth), user.BirthDate.toString())
            putString(getString(R.string.preference_email), user.email)
            putString(getString(R.string.preference_photo), user.photo)
            putString(getString(R.string.preference_password), user.password)
            commit()
        }
    }

    private fun getSharedPreferences(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        preferences.let {
            val nome = it.getString(getString(R.string.preference_name), "")
            val birthDate = it.getString(getString(R.string.preference_birth), "")
            val email = it.getString(getString(R.string.preference_email), "")

            Log.i(TAG, "nome: $nome, birthDate: $birthDate, email: $email")
        }

    }

    fun onClickSave(view: View) {

        setSharedPreferences()

        getSharedPreferences()
    }
}
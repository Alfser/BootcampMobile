package com.example.profilescreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.profilescreen.databinding.ActivityMainBinding
import com.example.profilescreen.databinding.MainDrawerLayoutBinding
import java.sql.Date


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private lateinit var binding:MainDrawerLayoutBinding
    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var nameHeader: TextView
    lateinit var emailHeader: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainDrawerLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
        setupDrawer()
        setSharedPreferences()
    }

    private fun initViews(){
        activityMainBinding = binding.activityMain
        val drawNavHeader = binding.mainDrawNavView.getHeaderView(0)
        nameHeader = drawNavHeader.findViewById(R.id.drawer_profile_name)
        emailHeader = drawNavHeader.findViewById(R.id.drawer_profile_email)
    }

    private fun setupDrawer(){
        val toolbar: Toolbar = activityMainBinding.mainToolbar
        setSupportActionBar(toolbar)
        val drawer = binding.drawerLayout
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

        nameHeader.text = user.name
        emailHeader.text = user.email
    }

    private fun getSharedPreferences(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        preferences.let {
            val name = it.getString(getString(R.string.preference_name), "")
            val birthDate = it.getString(getString(R.string.preference_birth), "")
            val email = it.getString(getString(R.string.preference_email), "")

            activityMainBinding.mainTvName.text = name
            activityMainBinding.mainTvDate.text = birthDate
            activityMainBinding.mainTvEmail.text = email

            Log.i(TAG, "nome: $name, birthDate: $birthDate, email: $email")
        }

    }

    fun onClickSave(view: View) {
        getSharedPreferences()
    }
}
package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.everis.bootcamp.threading.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadData.setOnClickListener {
            launchAstroTask()
        }
    }


    fun showData(astroList: List<AstrosPeople>){
        binding.textAstronauta.text = ""
        astroList.forEach {
            binding.textAstronauta.append("${it.name} - ${it.craft}\n\n")
        }
    }


    private fun showLoading(){
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.progress.visibility = View.GONE
    }

    private fun launchAstroTask(){
        val task = TaskAstros()
        task.execute()
    }

    inner class TaskAstros(): AsyncTask<Void, Int, List<AstrosPeople>>(){
        private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoading()
        }

        override fun doInBackground(vararg params: Void?): List<AstrosPeople> {
            return repository.loadData()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)


        }

        override fun onPostExecute(result: List<AstrosPeople>?) {
            super.onPostExecute(result)

            hideLoading()

            showData(result as List<AstrosPeople>)
        }
    }

}

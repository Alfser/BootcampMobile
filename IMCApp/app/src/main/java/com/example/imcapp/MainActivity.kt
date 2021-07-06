package com.example.imcapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var edPeso: EditText
    lateinit var edAltura: EditText
    lateinit var tvResultado: TextView
    lateinit var tvLabelResultado:TextView
    lateinit var btCalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewComponents()
        onClickCalculate()
    }

    fun onClickCalculate(){
        btCalcular.setOnClickListener {calculeImc()}
    }
    
    fun initViewComponents(){
        edPeso = findViewById(R.id.edit_text_peso)
        edAltura = findViewById(R.id.edit_text_altura)
        tvResultado = findViewById(R.id.text_view_result)
        tvLabelResultado = findViewById(R.id.label_result)
        btCalcular = findViewById(R.id.button_calcule)
    }

    fun showImcResult(imc: Float){

        tvLabelResultado.visibility = View.VISIBLE
        tvResultado.text = imc.toString()
        tvResultado.visibility = View.VISIBLE

    }

    fun calculeImc(){
        val peso: Float = edPeso.text.toString().toFloat()
        val altura: Float = edAltura.text.toString().toFloat()
        val imc = peso/(altura*altura)

        showImcResult(imc)
    }
}
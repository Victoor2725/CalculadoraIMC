package com.example.calcularimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calcularimc.model.CalculoIMC
import com.example.calcularimc.model.DetallesActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var etPeso: TextInputEditText
    private lateinit var etAltura: TextInputEditText
    private lateinit var tvResultado: TextView
    private lateinit var btnDetalles: Button

    private var imcActual: Double = 0.0
    private var categoriaActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btnDetalles = findViewById(R.id.btnDetalles)

        findViewById<Button>(R.id.btnCalcular).setOnClickListener {
            calcularIMC()
        }

        findViewById<Button>(R.id.btnLimpiar).setOnClickListener {
            limpiar()
        }

        btnDetalles.setOnClickListener {
            val intent = Intent(this, DetallesActivity::class.java)
            val calculo = CalculoIMC(
                peso = etPeso.text.toString().toDouble(),
                altura = etAltura.text.toString().toDouble(),
                imc = imcActual,
                categoria = categoriaActual
            )
            intent.putExtra("calculo", calculo)
            startActivity(intent)
        }
    }

    private fun calcularIMC() {
        val pesoStr = etPeso.text.toString()
        val alturaStr = etAltura.text.toString()

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Datos faltantes")
                .setMessage("Por favor, ingrese peso y altura.")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val peso = pesoStr.toDouble()
        val altura = alturaStr.toDouble()

        imcActual = peso / (altura * altura)
        val imcRedondeado = String.format("%.1f", imcActual).toDouble()
        categoriaActual = obtenerCategoria(imcRedondeado)

        tvResultado.text = "IMC: $imcRedondeado - $categoriaActual"
        btnDetalles.isEnabled = true
    }

    private fun obtenerCategoria(imc: Double): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc < 24.9 -> "Normal"
            imc < 29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    private fun limpiar() {
        etPeso.text?.clear()
        etAltura.text?.clear()
        tvResultado.text = ""
        btnDetalles.isEnabled = false
    }
}
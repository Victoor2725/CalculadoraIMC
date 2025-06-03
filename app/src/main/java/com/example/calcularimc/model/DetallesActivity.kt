package com.example.calcularimc.model

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calcularimc.R

class DetallesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val calculo = intent.getSerializableExtra("calculo") as CalculoIMC

        val texto = """
            Peso: ${calculo.peso} kg
            Altura: ${calculo.altura} m
            IMC: ${"%.1f".format(calculo.imc)}
            Categoría: ${calculo.categoria}
            
            Recomendación: ${getRecomendacion(calculo.categoria)}
        """.trimIndent()

        findViewById<TextView>(R.id.tvDetalles).text = texto
    }

    private fun getRecomendacion(categoria: String): String {
        return when (categoria) {
            "Bajo peso" -> "Consulta con un nutricionista para aumentar de peso."
            "Normal" -> "Mantén tu estilo de vida saludable."
            "Sobrepeso" -> "Considera ejercicio regular y una dieta balanceada."
            "Obesidad" -> "Busca apoyo médico para un plan de reducción de peso."
            else -> "Categoría desconocida."
        }
    }
}
package com.example.calcularimc.model

import java.io.Serializable

data class CalculoIMC(
    val peso: Double,
    val altura: Double,
    val imc: Double,
    val categoria: String
) : Serializable
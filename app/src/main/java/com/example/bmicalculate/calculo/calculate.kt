package com.example.bmicalculate.calculo

import kotlin.math.pow

//Função do tipo Double que retorna o IMC
fun calculate(weight: Double, height: Double)= weight / height.pow(2)
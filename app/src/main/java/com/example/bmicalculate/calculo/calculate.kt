package com.example.bmicalculate.calculo

import android.content.Context
import com.example.bmicalculate.R
import kotlin.math.pow

/*******************************************************
 * Função do tipo Double que retorna o IMC do peso
 *******************************************************/
fun calculate(weight: Double, height: Double)= weight / (height / 100).pow(2)

/*********************************************************
 * Exemplo de uma função com chave que retorna Double
 *
 * Essa função retorna : Double
 * fun calculate(weight: Double, height: Double): Double {
 *        return weight / (height /100 ).pow(2)
 *  }
 **********************************************************/

/***************************************************************
* Função responsavel por retornas a classificação do IMC
*
* Arquivos como string.xml só são acessadas se falar do contexto,
* o local aonde está.
*************************************************************** */

fun getBMIClassification(bmi: Double, context: Context): String{
    return if(bmi <= 18.5){
        context.getString(R.string.underweight)
    }else if(bmi > 18.5 && bmi < 25.0){
        context.getString(R.string.normal_weight)
    }else if(bmi >=25.0 && bmi < 30.0){
        context.getString(R.string.over_weight)
    }else if(bmi >= 30.0 && bmi< 40.0){
        context.getString(R.string.obesity)
    }else{
        context.getString(R.string.morby_obesity)
    }
}
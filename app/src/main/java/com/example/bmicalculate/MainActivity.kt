package com.example.bmicalculate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculate.calculo.calculate
import com.example.bmicalculate.ui.theme.BMICalculateTheme

/***************************************************************************************************
 * Objetivo: Realizar a avalição do IMC do usuario através do desenvolvimento de app para SMARTPHONE
 * Data: 18/02/2023
 * Autor: Gustavo Henrique
 * Versão: 1.0
 ****************************************************************************************************/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculateTheme {

                CalculatorScreen()

            }
        }
    }
}


/************************************************************************
 * @Preview(showSystemUi = true)
 * serve para visualizar a tela do smarthone de forma atualizada.
 *
 *
 *
 * A função de composição é realizada dessa forma:
 *
 * @Composable
 * fun nomeDaFuncao()
 *
 * Ela é utilizada para desenhar a tela do smartphone.
 * Outra boa prática é escreve a função com a Primeira letra maiscula.
 *************************************************************************/

@Preview(showSystemUi = true)

@Composable
fun CalculatorScreen() {

    /***************************************************************
     *Criando variaveis de estado, resposaveis por armagenar
     * as informações digitadas nas caixas de texto.
     ***************************************************************/

    var weightState = rememberSaveable {
        mutableStateOf("")

    }

    var heightState = rememberSaveable() {
        mutableStateOf("")
    }

    var bmiState = rememberSaveable {
        mutableStateOf("")
    }

    /***************************************************************
     * Surface: Responsável por abrange todo o conteudo, sendo o pai
     * das Colluns, Rows ou Cards...
     ****************************************************************/
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            //Header
            Column(

                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(

                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)

                )

                Text(

                    text = stringResource(id = R.string.title),
                    fontSize = 30.sp,
                    color = Color(69, 121, 255),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp

                )

            }

            //Form

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)

            ) {

                Text(

                    text = stringResource(id = R.string.height_label),
                    modifier = Modifier.padding(start = 13.dp),
                    fontSize = 17.sp,
                    letterSpacing = 1.sp

                )

                OutlinedTextField(

                    //Passa os valores da variavel heightState para a caixa de texto
                    value = heightState.value,

                    onValueChange = {

                        /******************************************************************************
                         * logcat = mostra tudo que você está fazendo no smartphone no terminal.
                         *
                         *
                         * It é uma referencia aos valores que estão sendo passados para a caixa
                         * de texto(outline), esses valores são passados para a variavel heightState
                         * que as mantem salvas na caixa de texto, sem ela os valores são perdidos.
                         *******************************************************************************/

                        Log.i("Smartphone", it)

                        heightState.value = it

                    },

                    modifier = Modifier.padding(
                        top = 8.dp, start = 16.dp
                    ),

                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Text(

                    text = stringResource(id = R.string.weight_label),
                    modifier = Modifier
                        .padding(start = 13.dp)
                        .padding(top = 33.dp),
                    fontSize = 17.sp,
                    letterSpacing = 1.sp

                )

                OutlinedTextField(

                    //Passa os valores da variavel weightState para a caixa de texto
                    value = weightState.value,

                    onValueChange = {

                        Log.i("Smartphone", it)

                        weightState.value = it

                    },

                    modifier = Modifier.padding(
                        top = 8.dp, start = 16.dp
                    ),

                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                //Colocando um espaço entre a caixa de texto e o botão
                Spacer(modifier = Modifier.height(33.dp))
            }

            Button(
                onClick = {
                    bmiState.value = calculate(
                        weight = weightState.value.toDouble(), height = heightState.value.toDouble()
                    ).toString()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 70.dp, end = 70.dp),
                shape = RoundedCornerShape(16.dp)

            ) {

                Text(
                    text = stringResource(id = R.string.button_calculate),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )

            }

            //Espaçamento entre o botão e o Card
            Spacer(modifier = Modifier.height(40.dp))

            //Footer
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(
                        topStart = 48.dp, topEnd = 48.dp
                    ), backgroundColor = Color(red = 88, green = 61, blue = 253, alpha = 255)
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),

                        //Mesmo espaço vertical entre cada objeto
                        verticalArrangement = Arrangement.SpaceEvenly,

                        //Centralizando todos os objetos no centro
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(

                            text = stringResource(id = R.string.your_score),
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Text(

                            text = bmiState.value,
                            color = Color.White,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Text(

                            text = stringResource(id = R.string.ideal_state),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Row() {

                            Button(onClick = {


                            }) {

                                Text(
                                    text = stringResource(id = R.string.reset), fontSize = 16.sp
                                )


                            }

                            //Colocando um espaço entre os buttons
                            Spacer(modifier = Modifier.width(40.dp))

                            Button(onClick = {


                            }
                            ) {

                                Text(

                                    text = stringResource(id = R.string.share),
                                    fontSize = 16.sp,


                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}
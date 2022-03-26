
package com.example.calcula_imc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calculateButton = findViewById<Button>(R.id.calculate_Button)
        val weight = findViewById<TextView>(R.id.weight_EditText)
        val height = findViewById<TextView>(R.id.height_EditText)
        val result = findViewById<TextView>(R.id.result_TextView)

        calculateButton.setBackgroundColor(R.color.black)

        //VALIDAÇÃO DE DADOS

        calculateButton.setOnClickListener {
            if(weight.text.toString() != "" && height.text.toString() != "" ) {
                //RETORNO DA FUNÇÃO calculate_IMC
                val imc = calculateIMC(weight.text.toString(), height.text.toString())
                //FORMATAÇÃO .00
                val df = DecimalFormat("#.00")
                //MOSTRA NA RESULTADO
                result.text = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
            }
            else{
                //RETORNA VALOR NULO CASO NÃO ENCONTRE UM VALOR VÁLIDO
                result.text = "Valor inválido!!! Tente novamente!!!"
            }

            it.hideKeyboard()
        }
    }

    //CALCULA IMC
    private fun calculateIMC(weight: String, height: String): Double = weight.toDouble() / ((height.toDouble()/100) * (height.toDouble()/100))

    //CASE PARA ENCONTRAR O TIPO DE IMC
    private fun checkIMC(db: Double): String{
        return when(db) {
            in 0.0..17.0 -> "Muito abaixo do peso."
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 ->  "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}

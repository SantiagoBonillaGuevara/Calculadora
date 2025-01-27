package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var nCurrent = "0"
    private var operator = ""
    private var n2 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btn0.setOnClickListener { escribirNumero("0") }
        binding.btn1.setOnClickListener { escribirNumero("1") }
        binding.btn2.setOnClickListener { escribirNumero("2") }
        binding.btn3.setOnClickListener { escribirNumero("3") }
        binding.btn4.setOnClickListener { escribirNumero("4") }
        binding.btn5.setOnClickListener { escribirNumero("5") }
        binding.btn6.setOnClickListener { escribirNumero("6") }
        binding.btn7.setOnClickListener { escribirNumero("7") }
        binding.btn8.setOnClickListener { escribirNumero("8") }
        binding.btn9.setOnClickListener { escribirNumero("9") }
        binding.btnCa.setOnClickListener { borrar() }
        binding.btnMas.setOnClickListener { escribirOperador("+") }
        binding.btnPor.setOnClickListener { escribirOperador("x") }
        binding.btnDivision.setOnClickListener { escribirOperador("/") }
        binding.btnMenos.setOnClickListener { escribirOperador("-") }
        binding.btnIgual.setOnClickListener { resultado() }
        binding.btnPunto.setOnClickListener { punto() }
    }

    private fun punto() {
        if(operator.isNullOrEmpty() && (nCurrent.isNullOrEmpty() or nCurrent.equals("0"))){
            nCurrent="0,"
        }else if(operator.isNullOrEmpty() && !nCurrent.isNullOrEmpty() && !nCurrent.equals("0") && !nCurrent.contains(",")){
            nCurrent+=","
        }else if(!operator.isNullOrEmpty() && (n2.isNullOrEmpty() or n2.equals("0"))){
            n2="0,"
        }else if(!operator.isNullOrEmpty() && !n2.isNullOrEmpty() && !n2.equals("0") && !n2.contains(",")){
            n2+=","
        }
    }

    private fun escribirOperador(operador: String) {
        if (operator.isNullOrEmpty() && !nCurrent.equals("0")) {
            operator = operador
            binding.tvNumber.setText(binding.tvNumber.getText().toString() + operator)
        } else if (!operator.isNullOrEmpty() && !operador.equals("-") && !operador.equals("%") && n2.isNullOrEmpty()) {
            operator = operador
            var currentText = binding.tvNumber.getText().toString()
            binding.tvNumber.setText(currentText.substring(0, currentText.length - 1) + operador)
        } else if (operador.equals("-")) {
            if (!operator.isNullOrEmpty() && n2.isNullOrEmpty()) {
                n2 = operador
                binding.tvNumber.setText(nCurrent + operator + n2)
            }
        }
    }

    private fun borrar() {
        binding.tvNumber.setText("0")
        nCurrent = "0"
        n2 = ""
        operator = ""
        binding.tvOperation.setText("")
    }

    private fun escribirNumero(n: String) {
        if (operator.isNullOrEmpty()) {
            if (!nCurrent.equals("0") && n.equals("0")) {
                nCurrent += n
            } else if (nCurrent.equals("0")) {
                nCurrent = n
            } else {
                nCurrent += n
            }
            binding.tvNumber.setText(nCurrent)
        } else {
            if (n2.isNullOrEmpty() && n.equals("0")) {
                n2 = n
            } else if (n2.equals("0") && !n.equals("0")) {
                n2 = n
            } else if (!n2.equals("0")) {
                n2 += n
            }
            binding.tvNumber.setText(nCurrent + operator + n2)
            Log.i("santiago", "el n2 actual es ${n2}")
        }
    }

    private fun resultado() {
        if (!operator.isNullOrEmpty() && !n2.isNullOrEmpty() && !nCurrent.isNullOrEmpty()) {
            binding.tvOperation.setText(binding.tvNumber.getText().toString())
            var resultado: Float = 0f
            var numero1 = nCurrent.replace("(", "").replace(")", "").replace(",", ".").toFloat()
            var numero2 = n2.replace("(", "").replace(")", "").replace(",", ".").toFloat()
            when (operator) {
                "+" -> {
                    resultado = numero1 + numero2
                }

                "-" -> {
                    resultado = numero1 - numero2
                }

                "/" -> {
                    if (numero2 != 0f) {
                        resultado = numero1 / numero2
                    } else {
                        borrar()
                    }
                }

                "x" -> {
                    resultado = numero1 * numero2
                }

                else -> {
                    borrar()
                }
            }
            n2=""
            operator=""
            nCurrent = if (resultado % 1 == 0f) {
                resultado.toInt().toString()
            } else {
                resultado.toString().replace(".",",")
            }
            binding.tvNumber.setText(nCurrent)
        }
    }
}
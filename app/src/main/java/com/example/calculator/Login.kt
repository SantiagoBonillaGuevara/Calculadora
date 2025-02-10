package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var conexion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        conexion = FirebaseAuth.getInstance()
        initComponents()
    }

    private fun initComponents() {
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnSign.setOnClickListener {
            navigateToSign()
        }
    }

    private fun login() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(applicationContext, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        conexion.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Acceso exitoso", Toast.LENGTH_SHORT).show()
                    navigateToHome()
                    finish()
                } else {
                    val errorMessage = task.exception?.message ?: "Acceso fallido"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToHome() {
        val intent = Intent(this@Login, Inicio::class.java)
        startActivity(intent)
    }

    private fun navigateToSign() {
        val intent = Intent(this, Sign::class.java)
        startActivity(intent)
    }
}
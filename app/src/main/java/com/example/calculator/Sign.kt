package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivitySignBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Sign : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding
    private lateinit var conexion: FirebaseAuth
    private lateinit var bdRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        conexion = FirebaseAuth.getInstance()
        initComponents()
    }

    private fun initComponents() {
        binding.btnRegistro.setOnClickListener {
            sign()
        }
    }

    private fun sign() {
        val nombre = binding.etNameRegister.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()

        createUser(nombre, email, password)
    }

    private fun createUser(nombre: String, email: String, password: String) {
        conexion.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(nombre, email, conexion.currentUser?.uid)
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } else {
                    Toast.makeText(getApplicationContext(), "Registro fallido", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun addUserToDatabase(nombre: String, email: String, uid: String?) {
        val user = User(nombre, email, uid)
        bdRef = FirebaseDatabase.getInstance().getReference()
        bdRef.child("users").child(uid!!).setValue(user)
    }

}
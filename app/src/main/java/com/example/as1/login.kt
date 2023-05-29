package com.example.as1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.as1.databinding.ActivityLoginBinding
import com.example.as1.db.DatabaseHelper

class login : AppCompatActivity() {
    lateinit var  db: DatabaseHelper
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnLogin.setOnClickListener {

            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            if (email.isEmpty() ||
                password.isEmpty()
            )

                Toast.makeText(this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show()
            else {
                val checkUser = db.checkUser(email, password)

                if (checkUser){
                    userLogin=true
                    val i = Intent(this, MyService::class.java)
                    ContextCompat.startForegroundService(this,i)
                    Toast.makeText(this, "LoginSuccessful", Toast.LENGTH_SHORT).show()
                }
                else {
                    val i = Intent(this, MyService::class.java)
                    ContextCompat.startForegroundService(this,i)
                    Toast.makeText(this, "wrong userName or password", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

    }
    companion object{
        var userLogin:Boolean = false

    }
}
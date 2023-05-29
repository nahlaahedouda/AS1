package com.example.as1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.as1.databinding.ActivitySignupBinding
import com.example.as1.db.DatabaseHelper

class signup : AppCompatActivity() {
    private lateinit var  db: DatabaseHelper
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db= DatabaseHelper(this)

        binding.btnSignup.setOnClickListener {

            val userName = binding.txtName.text.toString()
            val userEmail = binding.txtEmail.text.toString()
            val userPhoneNum = binding.txtPhoneNumber.text.toString()
            val userPassword = binding.txtPassword.text.toString()
            val confirmPassword = binding.txtConfirmPassword.text.toString()


            if (userName.isEmpty() ||
                userEmail.isEmpty() ||
                userPhoneNum.isEmpty() ||
                userPassword.isEmpty() ||
                confirmPassword.isEmpty())
            {
                Toast.makeText(this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show()
            }else{
                if(userPassword == confirmPassword){

                    val saveData= db.addUser(userName,userEmail,userPhoneNum.toInt(),userPassword)

                    if(saveData){
                        userSignup= true
                        val i = Intent(this, MyService::class.java)
                        ContextCompat.startForegroundService(this,i)
                        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                    }else{
                        val i = Intent(this, MyService::class.java)
                        ContextCompat.startForegroundService(this,i)
                        Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    companion object{
        var userSignup:Boolean = false

    }
}
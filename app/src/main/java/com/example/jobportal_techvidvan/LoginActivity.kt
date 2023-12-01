package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var register: TextView
    private lateinit var loginBtn: Button
    private lateinit var et_login_email: EditText
    private lateinit var et_login_password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register = findViewById(R.id.register)
        loginBtn = findViewById(R.id.loginBtn)
        et_login_email = findViewById(R.id.et_login_email)
        et_login_password = findViewById(R.id.et_login_password)

        register.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener {
            when{
                TextUtils.isEmpty(et_login_email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this@LoginActivity,"Enter Email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_login_password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this@LoginActivity,"Enter Password", Toast.LENGTH_SHORT).show()
                }

                else ->{
                    val email:String = et_login_email.text.toString().trim{it <= ' '}
                    val password:String = et_login_password.text.toString().trim{it <= ' '}
                    if(email!="admin@gmail.com"){
                        Log.d("Tag", "$email"+" "+"$password")
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Logged in Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(this@LoginActivity, UserActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }else{
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                }
            }
        }
    }
}
package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerBtn: Button
    private lateinit var et_register_email: EditText
    private lateinit var et_register_password: EditText
    private lateinit var login: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn = findViewById(R.id.registerBtn)
        et_register_email = findViewById(R.id.et_register_email)
        et_register_password = findViewById(R.id.et_register_password)
        login = findViewById(R.id.login)
        registerBtn.setOnClickListener {
            when{
                TextUtils.isEmpty(et_register_email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this@RegisterActivity,"Enter Email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_register_password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this@RegisterActivity,"Enter Password", Toast.LENGTH_SHORT).show()
                }

                else ->{
                    val email:String = et_register_email.text.toString().trim{it <= ' '}
                    val password:String = et_register_password.text.toString().trim{it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            { task ->
                                if(task.isSuccessful){
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this,"Registered Successfully", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@RegisterActivity, UserActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id",firebaseUser.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                            })
                }
            }
        }

        login.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
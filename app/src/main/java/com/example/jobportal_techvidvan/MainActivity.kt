package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var logoutBtn: Button
    private lateinit var addNewJob:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logoutBtn = findViewById(R.id.logoutBtn)
        addNewJob = findViewById(R.id.addNewJob)

        addNewJob.setOnClickListener {
            startActivity(Intent(this@MainActivity, InsertionActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}
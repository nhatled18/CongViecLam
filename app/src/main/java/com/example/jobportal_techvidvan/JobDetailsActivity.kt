package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JobDetailsActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var salary: TextView
    private lateinit var role: TextView
    private lateinit var startDate: TextView
    private lateinit var duration: TextView
    private lateinit var contact: TextView
    private lateinit var email: TextView
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

        name = findViewById(R.id.tvName)
        salary = findViewById(R.id.tvSalary)
        role = findViewById(R.id.tvRole)
        startDate = findViewById(R.id.tvStartDate)
        duration = findViewById(R.id.tvDuration)
        contact = findViewById(R.id.tvContact)
        email = findViewById(R.id.tvEmail)
        btnBack = findViewById(R.id.btnBack)

        name.text =intent.getStringExtra("name")
        salary.text =intent.getStringExtra("salary")
        role.text =intent.getStringExtra("role")
        startDate.text =intent.getStringExtra("startDate")
        duration.text =intent.getStringExtra("duration")
        contact.text =intent.getStringExtra("contact")
        email.text =intent.getStringExtra("email")

        btnBack.setOnClickListener {
            startActivity(Intent(this@JobDetailsActivity, FetchingActivity::class.java))
        }
    }
}
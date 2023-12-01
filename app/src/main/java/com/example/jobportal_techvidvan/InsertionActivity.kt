package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.joinAll
import java.io.DataInput

class InsertionActivity : AppCompatActivity() {
    private lateinit var etcompanyName: EditText
    private lateinit var etrole: EditText
    private lateinit var etsalary: EditText
    private lateinit var etstartDate: EditText
    private lateinit var etDuration: EditText
    private lateinit var etcontact: EditText
    private lateinit var etemail: EditText
    private lateinit var btnSave:Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etcompanyName = findViewById(R.id.etCompanyName)
        etrole = findViewById(R.id.etRole)
        etsalary = findViewById(R.id.etSalary)
        etstartDate = findViewById(R.id.etStartDate)
        etDuration = findViewById(R.id.etDuration)
        etcontact = findViewById(R.id.etContact)
        etemail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")
        btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = etcompanyName.text.toString()
        val role = etrole.text.toString()
        val salary = etsalary.text.toString()
        val startDate = etstartDate.text.toString()
        val duration = etDuration.text.toString()
        val contact = etcontact.text.toString()
        val email = etemail.text.toString()
        val id = dbRef.push().key!!

        if(name.isEmpty() || role.isEmpty() || salary.isEmpty() || startDate.isEmpty() || duration.isEmpty() || contact.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show()
        }else{
            val jobModel = JobModel(id,name, role, salary, startDate, duration, contact, email)
            dbRef.child(id).setValue(jobModel)
                .addOnCompleteListener {
                    Toast.makeText(this, "Job data saved successfully", Toast.LENGTH_SHORT).show()
                    etcompanyName.text.clear()
                    etrole.text.clear()
                    etsalary.text.clear()
                    etstartDate.text.clear()
                    etDuration.text.clear()
                    etcontact.text.clear()
                    etemail.text.clear()

            startActivity(Intent(this@InsertionActivity, MainActivity::class.java))
                }
        }

    }
}
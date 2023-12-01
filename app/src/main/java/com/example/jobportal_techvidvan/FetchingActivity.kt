package com.example.jobportal_techvidvan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var dbRef: DatabaseReference
    private lateinit var jobList: ArrayList<JobModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        jobRecyclerView = findViewById(R.id.rvJob)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.hasFixedSize()

        jobList = arrayListOf<JobModel>()

        getJobData()
    }

    private fun getJobData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()
                if(snapshot.exists()){
                    for(jobSnap in snapshot.children){
                        val jobData =jobSnap.getValue(JobModel::class.java)
                        jobList.add(jobData!!)
                    }

                    val mAdapter =JobAdapter(jobList)
                    jobRecyclerView.adapter =mAdapter

                    mAdapter.setOnItemClickListener(object : JobAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity, JobDetailsActivity::class.java)
                            intent.putExtra("name",jobList[position].name)
                            intent.putExtra("role",jobList[position].role)
                            intent.putExtra("salary",jobList[position].salary)
                            intent.putExtra("startDate",jobList[position].startDate)
                            intent.putExtra("duration",jobList[position].duration)
                            intent.putExtra("contact",jobList[position].contact)
                            intent.putExtra("email",jobList[position].email)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}
package com.example.jobportal_techvidvan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private val jobList: ArrayList<JobModel>): RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):JobAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.jon_list_item,parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: JobAdapter.ViewHolder, position: Int) {
        val currentJob = jobList[position]
        holder.tvName.text =currentJob.name
        holder.tvRole.text=currentJob.role
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    class ViewHolder(itemView: View,clickListener:onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvRole: TextView = itemView.findViewById(R.id.tvRole)
        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}
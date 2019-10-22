package com.example.estel.greenspacesmanagement.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.estel.greenspacesmanagement.view.DetailMaintenanceTask
import com.example.estel.greenspacesmanagement.R
import com.example.estel.greenspacesmanagement.R.id.task_card_view
import com.example.estel.greenspacesmanagement.model.MaintenanceTasks
import kotlinx.android.synthetic.main.task_list_row_layout.view.*

class TaskAdapter(val items: List<MaintenanceTasks>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.task_list_row_layout,parent,
                false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTask(items[position])
        holder.itemCardView.setOnClickListener { v: View ->
            val task = items[position]
            val intent = Intent(context, DetailMaintenanceTask::class.java)
            intent.putExtra("task", task)
            context!!.startActivity(intent)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var itemCardView: CardView = view.findViewById(task_card_view)

        fun bindTask(task : MaintenanceTasks) {
            with(itemView) {
                task_type.text = task.type
            }
        }



    }
}
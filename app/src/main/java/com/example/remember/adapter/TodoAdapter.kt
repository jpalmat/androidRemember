package com.example.remember.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.remember.R
import com.example.remember.`interface`.TodoListener
import com.example.remember.model.Customer
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(var todos: List<Customer>, var listenera: TodoListener): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            txtTodo.text = todos[position].name
            chDone.isChecked = true
        }

        holder.itemView.setOnClickListener {
            listenera.todoFrag(todos[position].name, true)
        }
    }

}
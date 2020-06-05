package com.example.remember

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remember.`interface`.TodoListener
import com.example.remember.adapter.Todo
import com.example.remember.adapter.TodoAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listener = activity as TodoListener

        var todoList = mutableListOf(
            Todo("hello", false),
            Todo("bye", true)
        )

        val adapter = TodoAdapter(todoList, listener)

        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        rootView.recycler.adapter = adapter
        rootView.recycler.layoutManager = LinearLayoutManager(activity)
        return rootView
    }
}
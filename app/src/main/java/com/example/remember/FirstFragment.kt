package com.example.remember

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remember.`interface`.TodoListener
import com.example.remember.adapter.Todo
import com.example.remember.adapter.TodoAdapter
import com.example.remember.model.Customer
import com.example.remember.retrofit.Api
import com.example.remember.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)

        //retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(Api::class.java)

        val callCustomer: Call<List<Customer>> = apiInterface.getCustomer()

        callCustomer.enqueue(object : Callback<List<Customer>> {
            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                val customers: List<Customer> = response.body()!!

                val adapter = TodoAdapter(customers, listener)

                rootView.recycler.adapter = adapter
                rootView.recycler.layoutManager = LinearLayoutManager(activity)
            }

        })



//        var todoList = mutableListOf(
//            Todo("Jimmy", false),
//            Todo("M", true)
//        )

        return rootView
    }
}
package com.example.remember

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        //retrofit https://www.youtube.com/watch?v=w4ep-Vwwhlk&list=PLzffTJx5aHaTooddd8mtk4VIVRMyJCxpH&index=1
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.129:8081/")
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

        rootView.btnAddTodo.setOnClickListener {
            val customer = Customer(3, rootView.enewtodo.text.toString())
            var call = apiInterface.saveCustomer(customer)
            call.enqueue(object : Callback<Customer> {
                override fun onFailure(call: Call<Customer>, t: Throwable) {

                }

                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG)
                }

            })
        }

        return rootView
    }
}
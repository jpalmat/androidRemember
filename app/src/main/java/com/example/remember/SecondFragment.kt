package com.example.remember

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_second, container, false)


//        val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
//        if (sharedPref != null) {
        if(arguments != null){
//            rootView.secondFragment.text = sharedPref.getString("name", null)
            rootView.secondFragment.text = arguments!!.getString("name")
        }

//        }

        return rootView
    }
}
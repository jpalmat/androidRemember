package com.example.remember

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser(){
        val email = etxtEmail.text.toString()
        val pass = etxtPassword.text.toString()//Pass01
        if(email.isNotEmpty() && pass.isNotEmpty()){
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    auth.createUserWithEmailAndPassword(email, pass)
//                    withContext(Dispatchers.Main){
//                        checkLoggedInState()
//                    }
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Successfully Registered", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, "Registration Failed", Toast.LENGTH_LONG).show()
                        }
//                    } .addOnCompleteListener(this, OnCompleteListener{ task ->
//                        if(task.isSuccessful){
//                            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
//                            val intent = Intent(this, MainActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }else {
//                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
//                        }
//                    })
//                    }
//                } catch (e: Exception){
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_LONG)
//                    }

                }
//            }
        }
    }

    private fun checkLoggedInState(){
        if(auth.currentUser==null){
            Toast.makeText(this, "You are not loogged in", Toast.LENGTH_LONG)
        } else {
            Toast.makeText(this, "You are loogged in", Toast.LENGTH_LONG)
        }
    }
}
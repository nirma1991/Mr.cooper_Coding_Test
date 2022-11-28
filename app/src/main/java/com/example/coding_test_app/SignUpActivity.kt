package com.example.coding_test_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var signinText: TextView;
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Binding
        btnLogin = findViewById(R.id.mRegisterBtn)
        signinText = findViewById(R.id.mSignInBtn)
        etEmail = findViewById(R.id.mUsernameEt)
        etPass = findViewById(R.id.mPasswordEt)

        // initialising Firebase auth object
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        signinText.setOnClickListener {
            val intent = Intent(this@SignUpActivity,LoginActivity::class.java);
            startActivity(intent);
        }

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity,LoginActivity::class.java);
                startActivity(intent);
                finish()
            } else {
                Toast.makeText(this, "Signed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
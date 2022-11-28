package com.example.coding_test_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.coding_test_app.meetings.MeetingsActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var RegisterText: TextView;
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // View Binding
        RegisterText = findViewById(R.id.mRegisterBtn);
        btnLogin = findViewById(R.id.mSignInBtn)
        etEmail = findViewById(R.id.mUsernameEt)
        etPass = findViewById(R.id.mPasswordEt)

        // initialising Firebase auth object
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        btnLogin.setOnClickListener {
            login()
        }
        RegisterText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java);
            startActivity(intent);
        }

    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MeetingsActivity::class.java);
                startActivity(intent);
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

}
package com.candroid.candroidrcc026

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.candroid.candroidrcc026.databinding.ActivityLoginBinding
import com.candroid.candroidrcc026.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.newUserTv.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            //TODO: Login for our existing user
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        if(auth.currentUser!!.isEmailVerified){
                            val i = Intent(this,HomeActivity::class.java)
                            startActivity(i)
                            finish() }
                        else
                            Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                    } } }
        binding.forgetPassTv.setOnClickListener {
            //كتبنا هنا البايندنج ومسحنا الفيربول اللي تحت بدل ميل ادريس خليناها ايميل
            val email = binding.emailEt.text.toString()
            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                    }
                } } } }
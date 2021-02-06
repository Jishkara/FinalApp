package com.example.ladifinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var mAuth : FirebaseAuth
    companion object{
        val pictures = ArrayList<PictureLink>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null){
            val intent = Intent(this,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginEmail = findViewById(R.id.inputEmail)
        loginPassword = findViewById(R.id.inputPassword)
        loginButton = findViewById(R.id.loginButton)
        signUpButton = findViewById(R.id.signUpButton)

        loginButton.setOnClickListener {

            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please Fill Both Credentials", Toast.LENGTH_SHORT).show()
            }else{
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val intent = Intent(this,UploadActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        signUpButton.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}
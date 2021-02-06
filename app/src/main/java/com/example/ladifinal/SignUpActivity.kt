package com.example.ladifinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var registerEmail : EditText
    private lateinit var registerPassword : EditText
    private lateinit var registerPassword2 : EditText
    private lateinit var registerButton: Button
    private lateinit var backButton: Button
    private lateinit var mAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        registerEmail = findViewById(R.id.signUpEmail)
        registerPassword = findViewById(R.id.signUpPassword)
        registerPassword2 = findViewById(R.id.signUpPassword2)
        registerButton = findViewById(R.id.registerButton)
        backButton = findViewById(R.id.backButton)


        registerButton.setOnClickListener {
            val email = registerEmail.text.toString()
            val password = registerPassword.text.toString()
            val password2 = registerPassword2.text.toString()
            if(email.isEmpty() || password.isEmpty() || password2.isEmpty()){
                Toast.makeText(this, "Boxes Can't Be Empty", Toast.LENGTH_SHORT).show()
            } else {
                if(password == password2){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
        backButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
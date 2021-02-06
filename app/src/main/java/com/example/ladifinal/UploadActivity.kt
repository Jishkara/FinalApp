package com.example.ladifinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UploadActivity : AppCompatActivity() {

    private lateinit var urlInput : EditText
    private lateinit var personName : EditText
    private lateinit var saveButton: Button
    private lateinit var signOutButton: Button
    private lateinit var goToRecycler: Button
    private lateinit var db : DatabaseReference
    private lateinit var reference2 : DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference.child("UserLinks")
        reference2 = FirebaseDatabase.getInstance().reference

        urlInput = findViewById(R.id.urlInput)
        personName = findViewById(R.id.personName)
        saveButton = findViewById(R.id.saveButton)
        signOutButton = findViewById(R.id.signOutButton)
        goToRecycler = findViewById(R.id.goToRecycler)

        signOutButton.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        saveButton.setOnClickListener {
            val url = urlInput.text.toString()
            var name = personName.text.toString()
            val link = PictureLink(url,name)
            var isInList : Boolean = false
            if(urlInput.text == null){
                Toast.makeText(this, "Can't Be Null", Toast.LENGTH_SHORT).show()
            }else{
                if(mAuth.currentUser?.uid != null){
                    db.child(mAuth.currentUser?.uid!!).setValue(link).addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            urlInput.text = null
                            personName.text = null
                            for(i in MainActivity.pictures){
                                if(i.link == url && i.name == name){
                                    isInList = true
                                }
                            }
                            if(!isInList && url != null && name != null){
                                MainActivity.pictures.add(PictureLink(url, name))
                            }
                        }else{
                            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        goToRecycler.setOnClickListener {
            val intent = Intent(this,RecyclerActivity::class.java)
            reference2.child("UserLinks").addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    return
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val x = it.getValue(PictureLink::class.java)
                        val url = x?.link
                        val name = x?.name
                        var isInList : Boolean = false
                        for(i in MainActivity.pictures){
                            if(i.link == url && i.name == name){
                                isInList = true
                            }
                        }
                        if(!isInList && url != null && name != null){
                            MainActivity.pictures.add(PictureLink(url, name))
                        }
                    }
                }
            })
            startActivity(intent)
        }

    }
}
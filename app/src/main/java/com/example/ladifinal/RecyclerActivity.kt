package com.example.ladifinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        supportActionBar?.hide()
        recyclerView = findViewById(R.id.recyclerView)

        val adapter = Adapter(MainActivity.pictures,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this,1)
    }
}
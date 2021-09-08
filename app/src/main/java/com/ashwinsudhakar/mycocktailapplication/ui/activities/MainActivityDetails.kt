package com.ashwinsudhakar.mycocktailapplication.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ashwinsudhakar.mycocktailapplication.R
import com.bumptech.glide.Glide

class MainActivityDetails : AppCompatActivity() {

    private var pName: TextView? = null
    private var pInstructions: TextView? = null
    private var pGlass: TextView? = null
    private var titleToolbar: TextView? = null
    private var backIcon: ImageView? = null
    private var pImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_details)
        initViews()
        initToolbar()


        pName!!.text = intent.getStringExtra("NAME").toString()
        Glide.with(this).load(intent.getStringExtra("IMAGE").toString())
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(pImage!!)

        pInstructions!!.text = intent.getStringExtra("INSTRUCTIONS").toString()
        pGlass!!.text = "Serve: " + intent.getStringExtra("GLASS").toString()

    }

    private fun initViews() {
        pName = findViewById(R.id.pName)
        pImage = findViewById(R.id.pImage)
        pInstructions = findViewById(R.id.pInstructions)
        pGlass = findViewById(R.id.pGlass)
        backIcon = findViewById(R.id.back_icon)
        titleToolbar = findViewById(R.id.title_toolbar)
    }

    private fun initToolbar() {

        titleToolbar!!.text = "Details"
        backIcon!!.setOnClickListener { onBackPressed() }
    }

}
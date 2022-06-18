package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.databinding.ActivityProfileBinding
import com.example.myapp.databinding.Fragment4Binding


class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

}

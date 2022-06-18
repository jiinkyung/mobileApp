package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.preference.PreferenceManager
import com.example.myapp.databinding.ActivityAddBinding
import com.example.myapp.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        fun getByteArrayFromDrawable(d: Drawable): ByteArray {
            val bitmap = d.toBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

            var data = ByteArray(bitmap.byteCount)
            data = stream.toByteArray();

            return data

        }
        binding.btn.setOnClickListener {
            val product = binding.product.text.toString()
            val price = binding.price.text.toString()

            // DB에 저장하기
            val db = DBHelper(this).writableDatabase
            db.execSQL(
                "insert into product_tb (name, price) values (?, ?)",
                arrayOf<String>(product, price)
            )

            db.close()

            intent.putExtra("product", product)
            intent.putExtra("price", price)
            setResult(RESULT_OK, intent)

            finish()
        }

    }
    override fun onResume() {
        super.onResume()
        val btnColor = sharedPreferences.getString("btnColor", "")
        if(btnColor == ""){
            binding.btn.setBackgroundColor(Color.parseColor("#A0D1F7"))

        } else {
            binding.btn.setBackgroundColor(Color.parseColor(btnColor))

        }

    }
}
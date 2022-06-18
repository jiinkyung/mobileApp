package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.Fragment3Binding


class Fragment3 : Fragment() {
    lateinit var binding : Fragment3Binding
    lateinit var adapter : MyAdapter2
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())
        // Inflate the layout for this fragment

        var datas : MutableList<String>? = null
        var prices : MutableList<String>? = null

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(getActivity())
        adapter = MyAdapter2(datas, prices)
        binding.mainRecyclerView.adapter = adapter


        val requestLancher: ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val d3 = it.data!!.getStringExtra("product")?.let {
                datas?.add(it) //mutablelist에 값 추가
            }
            val p = it.data!!.getStringExtra("price")?.let {
                prices?.add(it)
            }
            adapter.notifyDataSetChanged()

        }

        binding.btn.setOnClickListener {
            val intent = Intent(getActivity(), AddActivity::class.java)
            requestLancher.launch(intent)
        }

        datas = mutableListOf<String>()
        prices = mutableListOf<String>()

        //DB 읽어오기
        val db = DBHelper(getActivity()).readableDatabase
        val cursor = db.rawQuery("select * from product_tb", null)
        while(cursor.moveToNext()){
            datas?.add(cursor.getString(1))
            prices?.add(cursor.getString(2))
        }
        db.close()

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(getActivity())
        adapter = MyAdapter2(datas, prices)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.addItemDecoration( // 투두 항목 구분선
            DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL)
        )

        return binding.root
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
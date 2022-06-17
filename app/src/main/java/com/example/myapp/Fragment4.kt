package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.Fragment4Binding


class Fragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: Fragment4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment4Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)

        binding.btnSetting.setOnClickListener {
            val intent = Intent(getActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.logoutBtn.setOnClickListener {
            MyApplication2.auth.signOut()
            MyApplication2.email = null
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }



}



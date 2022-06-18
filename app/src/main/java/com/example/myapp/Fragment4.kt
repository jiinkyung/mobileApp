package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.Fragment4Binding
import com.kakao.sdk.user.UserApiClient


class Fragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: Fragment4Binding
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment4Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())

        binding.btnSetting.setOnClickListener {
            val intent = Intent(getActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.logoutBtn.setOnClickListener {
            MyApplication2.auth.signOut()
            MyApplication2.email = null

            UserApiClient.instance.logout { error ->
                if(error != null) {
                    Toast.makeText(getActivity(), "로그아웃 실패", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(getActivity(), "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show()
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }
    override fun onResume() {
        super.onResume()
        val btnColor = sharedPreferences.getString("btnColor", "")
        if(btnColor == ""){
            binding.btnSetting.setBackgroundColor(Color.parseColor("#A0D1F7"))
            binding.logoutBtn.setBackgroundColor(Color.parseColor("#A0D1F7"))

        } else {
            binding.btnSetting.setBackgroundColor(Color.parseColor(btnColor))
            binding.logoutBtn.setBackgroundColor(Color.parseColor(btnColor))

        }

    }



}



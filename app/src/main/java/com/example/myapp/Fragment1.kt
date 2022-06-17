package com.example.myapp

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.Fragment1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding : Fragment1Binding
    lateinit var sharedPreferences : SharedPreferences
    lateinit var name : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())
        name = sharedPreferences.getString("name", "")!!
        binding.name.setText(name)
        /*val name = arguments?.getString("name", "")
        Log.d("mobileApp", arguments.toString())
        Log.d("mobileApp", "이름: $name" )
        binding.name.setText(name)

         */

        /*Handler().postDelayed(Runnable {
val name = arguments?.get("name").toString()

        Log.d("mobileApp", arguments.toString())
        Log.d("mobileApp", "이름: " + name)
        binding.name.setText(name)

        }, 1000) // 딜레이 시간 설정. (1초 = 1000)


         */

        return binding.root
    }
    override fun onResume() {
        super.onResume()

        val name = sharedPreferences.getString("name", "")
        Log.d("mobileApp", "$name")

        binding.name.setText(name)

    }

}
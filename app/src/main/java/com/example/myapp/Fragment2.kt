package com.example.myapp

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.Fragment1Binding
import com.example.myapp.databinding.Fragment2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
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

    lateinit var sharedPreferences : SharedPreferences
    lateinit var binding : Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())

        val fragment = RetrofitFragment()
        val bundle = Bundle()

        binding.searchBtn.setOnClickListener {
            fragment.arguments = bundle
            childFragmentManager.beginTransaction()
                .replace(R.id.activity_content, fragment)
                .commit()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val btnColor = sharedPreferences.getString("btnColor", "")
        if(btnColor == ""){
            binding.searchBtn.setBackgroundColor(Color.parseColor("#A0D1F7"))

        } else {
            binding.searchBtn.setBackgroundColor(Color.parseColor(btnColor))

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
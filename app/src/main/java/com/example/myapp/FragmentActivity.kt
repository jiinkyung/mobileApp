package com.example.myapp

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.databinding.ActivityFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentActivity : AppCompatActivity() {

    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>
        init {
            fragments = listOf(Fragment1(), Fragment2(), Fragment3(), Fragment4())
        }
        override fun getItemCount(): Int { // fragment 개수?
            //TODO("Not yet implemented")
            return fragments.size

        }
        override fun createFragment(position: Int): Fragment {
            //TODO("Not yet implemented")
            return fragments[position]
        }
    }

    val binding by lazy { ActivityFragmentBinding.inflate(layoutInflater) }
    lateinit var toogle : ActionBarDrawerToggle

    var datas : MutableList<String>? = null // 전역변수로 선언해줘야함.
    lateinit var adapter : MyAdapter2

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 페이지 넘기는 방향
        binding.viewpager.adapter = MyFragmentAdapter(this)

        val tabTitleArray = arrayOf("홈", "가맹점\n찾기", "중고\n거래", "나의\n온도")
        TabLayoutMediator(binding.tab1, binding.viewpager){
                tab1, position -> tab1.text = tabTitleArray[position]
        }.attach()

        datas = savedInstanceState?.let {
            it.getStringArrayList("mydatas")?.toMutableList()
        } ?:let{
            mutableListOf<String>()
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)


    }

    override fun onResume() {
        super.onResume()

        val bgColor = sharedPreferences.getString("color", "")
        if(bgColor == ""){
            binding.rootLayout.setBackgroundColor(Color.parseColor("#ffffff"))
        } else {
            binding.rootLayout.setBackgroundColor(Color.parseColor(bgColor))
        }

        val btnColor = sharedPreferences.getString("btnColor", "")
        if(btnColor == ""){
            val tabLayout = binding.tab1
            tabLayout.setBackgroundColor(Color.parseColor("#A0D1F7"));
        } else {
            binding.tab1.setBackgroundColor(Color.parseColor(btnColor))
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

}
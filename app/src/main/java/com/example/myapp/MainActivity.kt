package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>
        init {
            fragments = listOf(Fragment1(), Fragment2(), Fragment3(), Fragment4(), Fragment5())
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

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    //val binding2 by lazy { ActivityMenuBinding.inflate(layoutInflater) }
    lateinit var toogle : ActionBarDrawerToggle

    var datas : MutableList<String>? = null // 전역변수로 선언해줘야함.
    //lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        // binding에 있는 툴바를 액션바처럼 사용하겠다!
        //setSupportActionBar(binding.toolbar)

        /*
        toogle = ActionBarDrawerToggle(this, binding.drawer,
            R.string.drawer_open,
            R.string.drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toogle.syncState()

         */
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 페이지 넘기는 방향
        binding.viewpager.adapter = MyFragmentAdapter(this)
/*
        val tabTitleArray = arrayOf("홈", "가맹점\n찾기", "중고\n거래", "로컬\n푸드", "나의\n온도")
        TabLayoutMediator(binding.tab1, binding.viewpager){
                tab1, position -> tab1.text = tabTitleArray[position]
        }.attach()

 */
        TabLayoutMediator(binding.tab1, binding.viewpager){
                tab, position -> tab.text = "TAB ${position+1}"
        }.attach()


        /*
        val requestLancher: ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val d3 = it.data!!.getStringExtra("result")?.let{
                datas?.add(it) //mutablelist에 값 추가
                adapter.notifyDataSetChanged() // 리사이클러뷰에 추가
            }
            //Log.d("mobileApp", d3!!)
        }

         */
/*
        binding.mainDrawerView.setNavigationItemSelectedListener {

            if(it.title=="MENU 1") {
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("data1", "mobile")
                intent.putExtra("data2", "app")
                requestLancher.launch(intent)
            }

            true

        }

 */
        datas = savedInstanceState?.let {
            it.getStringArrayList("mydatas")?.toMutableList()
        } ?:let{
            mutableListOf<String>()
        }

        /*binding2.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(datas)
        binding2.mainRecyclerView.adapter = adapter
        binding2.mainRecyclerView.addItemDecoration( // 투두 항목 구분선
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

         */


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}
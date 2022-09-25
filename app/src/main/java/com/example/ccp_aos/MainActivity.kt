package com.example.ccp_aos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ccp_aos.Network.RetrofitClient.retrofitService
import com.example.ccp_aos.Network.RetrofitService
import com.example.ccp_aos.Network.categoryList
import com.example.ccp_aos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

//  바인딩을 전역 변수로 선언 -> 다른 클래스에서도 사용할 수 있게 하기 위해서
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val str = "Sun Sep 18 11:20:09 UTC 2022 There was an unexpected error (type=Not Found, status=404) No message available"
        var cateList = arrayListOf<categoryList>()


//      1. 바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)

//      2. 레이아웃 표시
        setContentView(binding.root)

//      3. 바인딩 변수를 이용하여 위젯 사용
        binding.check.setOnClickListener(){
            content.setText(str)
        }


//      생성된 레트로핏 객체 사용하여 데이터 받아오기
        retrofitService.get_categories().enqueue(object : Callback<categoryList>{
            override fun onResponse(call: Call<categoryList>, response: Response<categoryList>) {
                cateList.addAll(response.body() as List<categoryList>)
                binding.content.setText(cateList.toString())

//              배경 투명도 조절
                binding.mainlayout.setBackgroundColor(Color.parseColor("#5644e6"));

//              ProgressBar 숨기기
                binding.loadingBar.visibility=View.GONE

            }

            override fun onFailure(call: Call<categoryList>, t: Throwable) {

            }

        })

//      스피너 어댑터 선언
        val adapter : spinnerAdapter = spinnerAdapter(this, cateList)
        binding.category.adapter = adapter

//      스피너의 시작 위치 지정
//        category.setSelection(0)

//      스피너의 아이템이 클릭 되었을 때 동작
        binding.category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, Position: Int, p3: Long) {

                binding.content.setText("${Position}" + cateList[Position])
                
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


    }
}
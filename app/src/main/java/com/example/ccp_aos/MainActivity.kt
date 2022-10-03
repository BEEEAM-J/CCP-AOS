package com.example.ccp_aos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ccp_aos.Network.RetrofitClient.retrofitService
import com.example.ccp_aos.Network.categoryList
import com.example.ccp_aos.Network.jokes
import com.example.ccp_aos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

//  바인딩을 전역 변수로 선언 -> 다른 클래스에서도 사용할 수 있게 하기 위해서
    private  lateinit var binding: ActivityMainBinding

//    lateinit var 로 선언하면 nullable이 안되서 그냥 var로 선언
    private var category_pos : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//      카테고리 리스트 받아올 arraylist 생성
        var cateList = arrayListOf<categoryList>()

//      1. 바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)

//      2. 레이아웃 표시
        setContentView(binding.root)

//      터치 방지
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



//      생성된 레트로핏 객체 사용하여 데이터 받아오기
        retrofitService.get_categories().enqueue(object : Callback<categoryList>{
            override fun onResponse(call: Call<categoryList>, response: Response<categoryList>) {
//              카테고리 리스트 받아오기
                cateList.addAll(response.body() as List<categoryList>)

//              스피너 어댑터 선언
//              레트로핏은 비동기라서 어댑터를 레트로핏 응답이 오고, 리스트를 받아온 다음에 어댑터를 선언해줘야함
                var adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, cateList)
                binding.category.adapter = adapter

//              배경 투명도 조절
                binding.mainlayout.setBackgroundColor(Color.parseColor("#5644e6"));

//              ProgressBar 숨기기
                binding.loadingBar.visibility=View.GONE

//              터치 방지 해제
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }

            override fun onFailure(call: Call<categoryList>, t: Throwable) {
                binding.content.setText("통신 실패")
            }

        })


//      스피너의 시작 위치 지정
        category.setSelection(0)

//      스피너의 아이템이 클릭 되었을 때 동작
        binding.category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                // 선택된 카테고리를 String으로 받아옴
                category_pos = cateList[p2] as String

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

//      이미지 버튼이 눌리면 카테고리에 맞는 농담을 출력해야함
        binding.check.setOnClickListener(){
//            random이 선택되었을 때 Query를 null 처리
            if (category_pos == "random"){
                category_pos = null
            }

            retrofitService.get_jokes(category_pos).enqueue(object : Callback<jokes>{
                override fun onResponse(call: Call<jokes>, response: Response<jokes>) {

                    binding.content.setText("${response.body()?.value ?: String}")
                }

                override fun onFailure(call: Call<jokes>, t: Throwable) {
                    binding.content.setText("통신 실패")
                }

            })
        }


    }
}
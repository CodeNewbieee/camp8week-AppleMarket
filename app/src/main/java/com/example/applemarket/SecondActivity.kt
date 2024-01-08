package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityMainBinding
import com.example.applemarket.databinding.ActivitySecondBinding
import java.text.DecimalFormat

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            ivItemBackbtn.bringToFront() // 이미지 최상단 불러오기
            // 뒤로가기 클릭시 화면 종료 및 메인 페이지
            ivItemBackbtn.setOnClickListener {
                if(!isFinishing) finish()
            }
            // 숫자 콤마 추가
            val decimal = DecimalFormat("#,###")
            tvItemPrice.text="${decimal.format(1800000)}원"
        }

    }
}
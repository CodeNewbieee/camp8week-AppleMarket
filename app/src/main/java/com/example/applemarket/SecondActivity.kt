package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityMainBinding
import com.example.applemarket.databinding.ActivitySecondBinding
import java.text.DecimalFormat
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        var random = Random
//        random.nextDouble(30.0..50.0)

        with(binding) {
            ivItemBackbtn.bringToFront() // 이미지 최상단 불러오기
            // 뒤로가기 클릭시 화면 종료 및 메인 페이지
            ivItemBackbtn.setOnClickListener {
                if(!isFinishing) finish()
            }
            val priceFormat = DecimalFormat("#,###원") // 가격 숫자 콤마 추가
            ivItem.setImageResource(intent.getParcelableExtra<Merchandise>("data")!!.photo)
            tvItemSeller.setText(intent.getParcelableExtra<Merchandise>("data")!!.seller)
            tvItemLocation.setText(intent.getParcelableExtra<Merchandise>("data")!!.address)
            tvItemTitle.setText(intent.getParcelableExtra<Merchandise>("data")!!.title)
            tvItemInfo.setText(intent.getParcelableExtra<Merchandise>("data")!!.intro)
            tvItemPrice.text = priceFormat.format(intent.getParcelableExtra<Merchandise>("data")!!.price)
        }

    }
}
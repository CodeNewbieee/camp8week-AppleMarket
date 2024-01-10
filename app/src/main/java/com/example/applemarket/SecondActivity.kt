package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivitySecondBinding
import java.text.DecimalFormat
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var ram = Random
        var num = ram.nextDouble(30.0,60.0)

        with(binding) {
            ivItemBackbtn.bringToFront() // 이미지 최상단 불러오기

            ivItemBackbtn.setOnClickListener {// 뒤로가기 클릭시 화면 종료 및 메인 페이지
                if(!isFinishing) finish()
            }
            val tempFormat = DecimalFormat("##.#°C")
            tvItemTemp.text = tempFormat.format(num)

            when(num) {
                in 30.0..34.9 -> ivItemTemperature.setImageResource(R.drawable.lv1)
                in 35.0..39.9 -> ivItemTemperature.setImageResource(R.drawable.lv2)
                in 40.0..44.9 -> ivItemTemperature.setImageResource(R.drawable.lv3)
                in 45.0..49.9 -> ivItemTemperature.setImageResource(R.drawable.lv4)
                in 50.0..54.9 -> ivItemTemperature.setImageResource(R.drawable.lv5)
                in 55.0..60.0 -> ivItemTemperature.setImageResource(R.drawable.lv6)
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
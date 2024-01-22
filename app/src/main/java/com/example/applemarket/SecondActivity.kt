package com.example.applemarket

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.applemarket.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    private var isLike = false

    private val item :Merchandise? by lazy {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constans.ITEM_OBJECT, Merchandise::class.java)
        } else {
            intent.getParcelableExtra<Merchandise>(Constans.ITEM_OBJECT)
        }
    }

    private val itemPosition :Int by lazy {
        intent.getIntExtra(Constans.ITEM_INDEX,0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var ram = Random
        var num = ram.nextDouble(30.0,60.0)

        with(binding) {
            ivItemBackbtn.bringToFront() // 이미지 최상단 불러오기

            ivItemBackbtn.setOnClickListener {
                exit()
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

            // 메인에서 넘어온 객체 데이터를 받아서 각 뷰에다가 할당
            val priceFormat = DecimalFormat("#,###원") // 가격 숫자 콤마 추가
            ivItem.setImageResource(item?.photo ?: 0)
            tvItemSeller.setText(item?.seller)
            tvItemLocation.setText(item?.address)
            tvItemTitle.setText(item?.title)
            tvItemInfo.setText(item?.intro)
            tvItemPrice.setText(priceFormat.format(item?.price))

            isLike = item?.isLike == true
            ivItemHeart.setImageResource(if(isLike) R.drawable.ic_list_favorite_fill else R.drawable.ic_list_favorite_empty)

            ivItemHeart.setOnClickListener {
                if(!isLike){
                    ivItemHeart.setImageResource(R.drawable.ic_list_favorite_fill)
                    isLike = true
                    Snackbar.make(clSecond,"관심목록에 추가되었습니다.",2000).show()
                } else {
                    ivItemHeart.setImageResource(R.drawable.ic_list_favorite_empty)
                    isLike = false
                }

            }
        }
    }
    fun exit() {
        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra("itemIndex",itemPosition)
            putExtra("isLike",isLike)
        }
        setResult(RESULT_OK,intent)
        if(!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}
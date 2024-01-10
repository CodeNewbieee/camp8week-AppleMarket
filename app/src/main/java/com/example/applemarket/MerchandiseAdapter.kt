package com.example.applemarket

import android.content.Intent
import android.icu.text.DecimalFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ListItemBinding
import androidx.core.content.ContextCompat

class MerchandiseAdapter(private val list: List<Merchandise>) :RecyclerView.Adapter<MerchandiseAdapter.MerchandiseViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseViewHolder {
        Log.d("McAdapter","onCreateViewHolder")
        return MerchandiseViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MerchandiseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            Log.d("Click","onBindViewHolder : $position")
            itemClick?.onClick(it, position)
        }
        Log.d("McAdapter","onBindViewHolder : $position")
        holder.bind(list[position]) // 더미 데이터 순번당 데이터 연결(바인딩)
    }

    override fun getItemCount(): Int { // 전체 데이터 크기 알려주기
        return list.size
    }

    inner class MerchandiseViewHolder(private val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Merchandise) { // 레이아웃 데이터 아이디의 text와 더미리스트의 객체 데이터를 연결
            val priceFormat = DecimalFormat("#,###원") // 숫자 콤마 포맷 (가격)
            binding.ivList.setImageResource(item.photo)
            binding.tvListTitle.text = item.title
            binding.tvListLocation.text = item.address
            binding.tvListPrice.text = priceFormat.format(item.price)
            binding.tvListHeart.text = item.good.toString()
            binding.tvListChat.text = item.chat.toString()
        }
    }
}
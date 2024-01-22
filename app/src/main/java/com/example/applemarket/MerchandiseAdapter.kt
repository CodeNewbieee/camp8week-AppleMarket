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

    // 짧게 클릭시
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    // 길게 클릭시
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseViewHolder {
        Log.d("McAdapter", "onCreateViewHolder")
        return MerchandiseViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MerchandiseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {  //짧게 클릭시
            Log.d("Click", "onBindViewHolder click: $position")
            itemClick?.onClick(it, position) // 함수 실행
        }

        holder.itemView.setOnLongClickListener { // 길게 클릭시
            Log.d("Click", "onBindViewHolder Longclick: $position")
            itemLongClick?.onLongClick(it, position) // 함수 실행
            return@setOnLongClickListener true // ???
        }

        Log.d("McAdapter", "onBindViewHolder : $position")
        holder.bind(list[position]) // 더미 데이터 순번당 데이터 연결(바인딩)
    }

    override fun getItemCount(): Int { // 전체 데이터 크기 알려주기
        return list.size
    }

    inner class MerchandiseViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Merchandise) { // 레이아웃 데이터 아이디의 text와 더미리스트의 객체 데이터를 연결
            with(binding) {
                val priceFormat = DecimalFormat("#,###원") // 숫자 콤마 포맷 (가격)
                ivList.setImageResource(item.photo)
                tvListTitle.setText(item.title)
                tvListLocation.setText(item.address)
                tvListPrice.setText(priceFormat.format(item.price))
                tvListHeart.setText(item.good.toString())
                tvListChat.setText(item.chat.toString())
                if(list[position].isLike){
                    ivListHeart.setImageResource(R.drawable.ic_list_favorite_fill)
                } else {
                    ivListHeart.setImageResource(R.drawable.ic_list_favorite_empty)
                }

            }
        }
    }
}
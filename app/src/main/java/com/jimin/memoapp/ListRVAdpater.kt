package com.jimin.memoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jimin.memoapp.databinding.ItemMemolistBinding

class ListRVAdpater(private val itemList : ArrayList<Item>) : RecyclerView.Adapter<ListRVAdpater.ViewHoler>() {
    inner class ViewHoler(private val viewBinding: ItemMemolistBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : Item) {
            Log.d("Adapter",item.content)
            viewBinding.mainTv.text = item.content
        }
    }

    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        val viewBinding = ItemMemolistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHoler(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        holder.bind(itemList[position])
        holder.itemView.setOnClickListener {
            removeItems(position)
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener=itemClickListener


    }

    fun removeItems(position: Int) {
        itemList.removeAt(position)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = itemList.size
}
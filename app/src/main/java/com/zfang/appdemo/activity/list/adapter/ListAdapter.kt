package com.zfang.appdemo.activity.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.list.bean.ItemBean
import com.zfang.appdemo.databinding.ListItemLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {


    private val data: MutableList<ItemBean> = mutableListOf()

    fun setData(items: List<ItemBean>) {
        data.clear()
        data.addAll(items)
        notifyItemRangeInserted(0, data.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<ListItemLayoutBinding>(LayoutInflater.from(parent.context), R.layout.list_item_layout, parent, false)
        return ItemViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.d("zfang", "onBindViewHolder with payloads")
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {

            if (null != payloads.get(position) && payloads.get(position) is Int) {

                data.get(position).apply {
                    data.set(position, ItemBean(this.name, "New Info xxxxx no number"))
                    holder.bind(data.get(position))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("zfang", "onBindViewHolder normal")
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ItemViewHolder(val binding: ListItemLayoutBinding, item: View): RecyclerView.ViewHolder(item) {
        fun bind(itemBean: ItemBean) {
            binding.bean = itemBean
            binding.executePendingBindings()
        }

        fun update(itemBean: ItemBean) {
            bind(itemBean)
        }
    }
}
package com.zfang.appdemo.activity.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zfang.appdemo.activity.list.bean.ItemBean
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    val listLiveData = MutableLiveData<List<ItemBean>>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val list = mutableListOf<ItemBean>()
            for (index in 1..20) {
                list.add(ItemBean("Name$index", "XXX DDDD KKKK $index"))
            }
            listLiveData.value = list
        }
    }
}
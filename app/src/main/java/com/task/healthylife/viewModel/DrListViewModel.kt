package com.task.healthylife.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.healthylife.api.RetrofitInstance
import com.task.healthylife.model.ListOfDr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DrListViewModel:ViewModel() {

    private val _drList = MutableStateFlow<List<ListOfDr>>(emptyList())
    val drList: StateFlow<List<ListOfDr>> get() = _drList

    init {
        getDrList()
    }
    fun getDrList(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDrList()
                _drList.value = response.dr_list
            }catch (e:Exception){
                Log.e("getDrList","Error is ${e.message}")
            }
        }
    }
}
package com.task.healthylife.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.healthylife.api.RetrofitInstance
import com.task.healthylife.classes.DietType
import com.task.healthylife.model.DietDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DietPlanViewModel : ViewModel() {

    private val _dietPlan = MutableStateFlow<DietDetails?>(null)
    val dietPlan: StateFlow<DietDetails?> = _dietPlan


    fun getDietPlan(dietType: DietType) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDietPlan()
                when (dietType) {
                    DietType.WEIGHT_LOSS -> _dietPlan.value = response.dietPlans.weight_loss
                    DietType.WEIGHT_GAIN -> _dietPlan.value = response.dietPlans.weight_gain
                    DietType.WEIGHT_MAINTENANCE -> _dietPlan.value = response.dietPlans.weight_maintenance
                }
            } catch (e: Exception) {
                Log.e("k", "${e.message}")
            }
        }
    }
}
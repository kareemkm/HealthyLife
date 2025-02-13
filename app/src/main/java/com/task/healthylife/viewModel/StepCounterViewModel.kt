package com.task.healthylife.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.task.healthylife.model.StepRecord
import com.task.healthylife.room.StepRecordDao
import com.task.healthylife.room.StepRecordDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class StepCounterViewModel(application: Application) : AndroidViewModel(application),
    SensorEventListener {

    private val stepRecordDao: StepRecordDao =
        StepRecordDatabase.getDatabase(application).stepRecordDao()

    private val sensorManager: SensorManager =
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private val sharedPreferences =
        application.getSharedPreferences("step_prefs", Context.MODE_PRIVATE)
    private var initialStepCount: Int
        get() = sharedPreferences.getInt("initialStepCount", 0)
        set(value) = sharedPreferences.edit().putInt("initialStepCount", value).apply()

    private val _currentStepCount = MutableStateFlow(0)
    val currentStepCount: StateFlow<Int> = _currentStepCount

    val stepRecords: Flow<List<StepRecord>> = stepRecordDao.getAllStepsRecorde()

    init {
        registerSensor()
        resetStepsAtMidnight()
    }

    private fun registerSensor() {
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (initialStepCount == 0) {
                initialStepCount = it.values[0].toInt()
            }
            _currentStepCount.value = (it.values[0].toInt() - initialStepCount).coerceAtLeast(0)
        }
        saveCurrentStepCount()
    }

    private fun saveCurrentStepCount() {
        viewModelScope.launch {
            val currentDate =
                SimpleDateFormat("dd MMM yy", Locale.getDefault()).format(Date())
            val todayRecord = stepRecordDao.getStepRecordByDate(currentDate)

            if (todayRecord != null) {
                val updateRecord = todayRecord.copy(stepCount = _currentStepCount.value)
                stepRecordDao.updateStepRecord(updateRecord)

            } else {
                val newRecord = StepRecord(day = currentDate, stepCount = _currentStepCount.value)
                stepRecordDao.insertStepRecorde(newRecord)
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun resetStepsAtMidnight() {

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.DAY_OF_YEAR, 1)
        }
        val delay = calendar.timeInMillis - System.currentTimeMillis()

        viewModelScope.launch {

            delay(delay)
            saveCurrentStepCount()

            initialStepCount = 0
            _currentStepCount.value = 0
            sharedPreferences.edit().putInt("initialStepCount", 0).apply()
            resetStepsAtMidnight()

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }

    /**
    fun deleteCount(stepRecord: StepRecord) {
        viewModelScope.launch {
            stepRecordDao.deleteCountStep(stepRecord)
        }
    }
    */
}

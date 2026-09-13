package com.example.kekocino.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Vibrator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekocino.data.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class TimerViewModel(
    application: Application,
    initialMinutes: Int,
    user: User?
) : AndroidViewModel(application) {

    val minuteOptions = listOf(1, 3, 5, 10, 15, 20, 30)
    val alertModes = listOf("Solo destello", "Solo vibración", "Destello y vibración")

    var selectedMinutes by mutableIntStateOf(initialMinutes.coerceIn(1, 30))
        private set
    var selectedMode by mutableStateOf(defaultMode(user))
        private set
    var remainingSeconds by mutableIntStateOf(selectedMinutes * 60)
        private set
    var isRunning by mutableStateOf(false)
        private set
    var isAlerting by mutableStateOf(false)
        private set
    var flashOn by mutableStateOf(false)
        private set

    private val shouldFlash: Boolean
        get() = selectedMode == alertModes[0] || selectedMode == alertModes[2]
    private val shouldVibrate: Boolean
        get() = selectedMode == alertModes[1] || selectedMode == alertModes[2]

    private var countdownJob: Job? = null
    private var alertJob: Job? = null

    fun onModeChange(mode: String) {
        selectedMode = mode
        if (isAlerting) startAlert()
    }

    fun onMinutesChange(minutes: Int) {
        selectedMinutes = minutes
        remainingSeconds = minutes * 60
        stopAlert()
    }

    fun toggleRunning() {
        stopAlert()
        if (isRunning) {
            isRunning = false
            countdownJob?.cancel()
            return
        }
        isRunning = true
        countdownJob = viewModelScope.launch {
            while (isRunning && remainingSeconds > 0) {
                delay(1000.milliseconds)
                remainingSeconds--
            }
            if (isRunning && remainingSeconds == 0) {
                isRunning = false
                startAlert()
            }
        }
    }

    fun reset() {
        isRunning = false
        countdownJob?.cancel()
        stopAlert()
        remainingSeconds = selectedMinutes * 60
    }

    private fun startAlert() {
        alertJob?.cancel()
        isAlerting = true
        flashOn = false
        alertJob = viewModelScope.launch {
            launch {
                if (!shouldFlash) return@launch
                while (isActive) {
                    flashOn = !flashOn
                    delay(400.milliseconds)
                }
            }
            launch {
                while (isActive && shouldVibrate) {
                    vibrate()
                    delay(800.milliseconds)
                }
            }
        }
    }

    private fun stopAlert() {
        isAlerting = false
        flashOn = false
        alertJob?.cancel()
    }

    @Suppress("DEPRECATION")
    private fun vibrate() {
        val vibrator = getApplication<Application>()
            .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(500)
    }

    private fun defaultMode(user: User?): String {
        return when {
            user?.visualAlerts == true && user.vibration -> alertModes[2]
            user?.vibration == true -> alertModes[1]
            else -> alertModes[0]
        }
    }
}

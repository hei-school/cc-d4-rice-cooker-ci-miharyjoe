package org.example

import org.example.ErrorHandler.Companion.logError
import java.util.Timer
import java.util.TimerTask

class RiceCooker {
    internal var quantityOfRice: Int? = null
    internal var quantityOfWater: Int? = null
    internal var temperature: Int = 100
    internal var timer: Int = 30
    internal var currentStatus = "Idle"
    internal var currentTemperature = 0
    internal var currentTimer: Timer? = null
    internal var isCooking = false

    fun setupCooking(
        riceQuantity: Int?,
        waterQuantity: Int?,
        temperature: Int?,
        timer: Int?,
    ) {
        this.quantityOfRice = riceQuantity
        this.quantityOfWater = waterQuantity
        this.currentTemperature = temperature ?: this.temperature
        this.timer = timer ?: this.timer
        currentStatus = "Setup completed. Ready to start cooking."
        println("Setup completed. Ready to start cooking.")
    }

    fun startCooking() {
        try {
            if (quantityOfWater == null || quantityOfWater == 0) {
                currentStatus = "Insufficient water. Please add water before starting cooking."
                throwErrorAndLog("Insufficient water. Please add water before starting cooking.")
            }

            currentTimer?.cancel()
            currentTimer = Timer()
            currentTimer?.schedule(
                object : TimerTask() {
                    override fun run() {
                        currentStatus = "Cooking complete. Starting warm mode."
                        isCooking = false
                        warmMode()
                    }
                },
                timer * 60 * 1000L,
            )

            isCooking = true
            currentStatus = "Cooking started. Timer set for $timer minutes."
            println("Cooking started. Timer set for $timer minutes.")
        } catch (e: Exception) {
            val errorMessage = "Error starting cooking: ${e.message}"
            logError(errorMessage)
            println(errorMessage)
        }
    }

    fun warmMode() {
        try {
            if (quantityOfWater == null || quantityOfWater == 0) {
                throwErrorAndLog("Insufficient water. Please add water before starting Warm mode.")
                return
            }
            currentTemperature = 60
            currentStatus = "Warm mode activated. Temperature set to 60°C."
            println("Warm mode activated. Temperature set to 60°C.")
        } catch (e: Exception) {
            val errorMessage = "Error activating warm mode: ${e.message}"
            logError(errorMessage)
            println(errorMessage)
        }
    }

    fun pauseCooking() {
        try {
            if (isCooking) {
                currentTimer?.cancel()
                currentTemperature = 0
                isCooking = false
                currentStatus = "Cooking paused."
                println("Cooking paused.")
            } else {
                currentStatus = "No active cooking process to pause."
                println("No active cooking process to pause.")
            }
        } catch (e: Exception) {
            val errorMessage = "Error pausing cooking: ${e.message}"
            logError(errorMessage)
            println(errorMessage)
        }
    }

    fun resumeCooking() {
        try {
            if (isCooking) {
                currentStatus = "Already cooking. Cannot resume."
                println("Already cooking. Cannot resume.")
            } else {
                currentTimer = Timer()
                currentTimer?.schedule(
                    object : TimerTask() {
                        override fun run() {
                            currentStatus = "Cooking complete. Starting warm mode."
                            isCooking = false
                            warmMode()
                        }
                    },
                    timer * 60 * 1000L,
                )
                isCooking = true
                currentTemperature = temperature
                currentStatus = "Cooking resumed. Timer set for $timer minutes."
                println("Cooking resumed. Timer set for $timer minutes.")
            }
        } catch (e: Exception) {
            val errorMessage = "Error resuming cooking: ${e.message}"
            logError(errorMessage)
            println(errorMessage)
        }
    }

    fun checkStatus() {
        println("Status: $currentStatus")
        println("Temperature: $currentTemperature °C")
    }

    private fun throwErrorAndLog(message: String) {
        val error = Exception(message)
        logError(message)
        throw error
    }
}

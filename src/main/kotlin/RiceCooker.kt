package org.example

import java.util.*

class RiceCooker {
    private var quantityOfRice: Int? = null
    private var quantityOfWater: Int? = null
    private var temperature: Int = 100
    private var timer: Int = 30
    private var currentStatus = "Idle"
    private var currentTemperature = 0
    private var currentTimer: Timer? = null
    private var isCooking = false

    fun setupCooking(riceQuantity: Int?, waterQuantity: Int?, temperature: Int?, timer: Int?) {
        this.quantityOfRice = riceQuantity
        this.quantityOfWater = waterQuantity
        this.currentTemperature = temperature ?: this.temperature
        this.timer = timer ?: this.timer
        currentStatus = "Setup completed. Ready to start cooking."
        println("Setup completed. Ready to start cooking.")
    }

    fun startCooking() {
        if (quantityOfWater == null || quantityOfWater == 0) {
            println("Error: Insufficient water. Please add water before starting cooking.")
            return
        }

        currentTimer?.cancel()
        currentTimer = Timer()
        currentTimer?.schedule(object : TimerTask() {
            override fun run() {
                currentStatus = "Cooking complete. Starting warm mode."
                isCooking = false
                warmMode()
            }
        }, timer * 60 * 1000L)

        isCooking = true
        currentStatus = "Cooking started. Timer set for $timer minutes."
        println("Cooking started. Timer set for $timer minutes.")
    }

    fun warmMode() {
        if (quantityOfWater == null || quantityOfWater == 0) {
            println("Error: Insufficient water. Please add water before starting Warm mode.")
            return
        }
        currentTemperature = 60
        currentStatus = "Warm mode activated. Temperature set to 60°C."
        println("Warm mode activated. Temperature set to 60°C.")
    }

    fun pauseCooking() {
        if (isCooking) {
            currentTimer?.cancel()
            currentTemperature = 0
            isCooking = false
            currentStatus = "Cooking paused."
            println("Cooking paused.")
        } else {
            println("No active cooking process to pause.")
        }
    }

    fun resumeCooking() {
        if (isCooking) {
            println("Already cooking. Cannot resume.")
        } else {
            currentTimer = Timer()
            currentTimer?.schedule(object : TimerTask() {
                override fun run() {
                    currentStatus = "Cooking complete. Starting warm mode."
                    isCooking = false
                    warmMode()
                }
            }, timer * 60 * 1000L)
            isCooking = true
            currentTemperature = temperature
            currentStatus = "Cooking resumed. Timer set for $timer minutes."
            println("Cooking resumed. Timer set for $timer minutes.")
        }
    }

    fun checkStatus() {
        println("Status: $currentStatus")
        println("Temperature: $currentTemperature °C")
    }
}
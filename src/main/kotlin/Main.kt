package org.example

fun main() {
    val cooker = RiceCooker()

    println("Welcome to the Rice Cooker CLI!")

    while (true) {
        println("1. Setup Cooking")
        println("2. Start Cooking")
        println("3. Warm Mode")
        println("4. Pause Cooking")
        println("5. Resume Cooking")
        println("6. Check Status")
        println("7. Quit")

        print("Enter the number of the command: ")
        when (val userInput = readlnOrNull()) {
            "1" -> {
                print("Enter quantity of rice (can be null): ")
                val riceQuantity = readlnOrNull()?.toIntOrNull()

                print("Enter quantity of water (can be null): ")
                val waterQuantity = readlnOrNull()?.toIntOrNull()

                print("Enter temperature: ")
                val temperature = readlnOrNull()?.toIntOrNull()

                print("Enter timer (optional, default 30min): ")
                val timer = readlnOrNull()?.toIntOrNull()

                cooker.setupCooking(riceQuantity, waterQuantity, temperature, timer)
            }
            "2" -> cooker.startCooking()
            "3" -> cooker.warmMode()
            "4" -> cooker.pauseCooking()
            "5" -> cooker.resumeCooking()
            "6" -> cooker.checkStatus()
            "7" -> {
                println("Exiting Rice Cooker CLI. Goodbye!")
                break
            }
            else -> println("Invalid command. Please enter a valid number.")
        }
    }
}

package main

import (
	"fmt"
	"time"
)

type RiceCooker struct {
	quantityOfRice     *int
	quantityOfWater    *int
	temperature        int
	timer              int
	currentStatus      string
	currentTemperature int
	currentTimer       *time.Timer
	isCooking          bool
}

func (rc *RiceCooker) setupCooking(riceQuantity, waterQuantity, temperature, timer *int) {
	rc.quantityOfRice = riceQuantity
	rc.quantityOfWater = waterQuantity
	if temperature != nil {
		rc.currentTemperature = *temperature
	} else {
		rc.currentTemperature = rc.temperature
	}
	if timer != nil {
		rc.timer = *timer
	}
	rc.currentStatus = "Setup completed. Ready to start cooking."
	fmt.Println("Setup completed. Ready to start cooking.")
}

func (rc *RiceCooker) startCooking() {
	if rc.quantityOfWater == nil || *rc.quantityOfWater == 0 {
		fmt.Println("Error: Insufficient water. Please add water before starting cooking.")
		return
	}

	if rc.currentTimer != nil {
		rc.currentTimer.Stop()
	}
	rc.currentTimer = time.NewTimer(time.Duration(rc.timer) * time.Minute)

	go func() {
		<-rc.currentTimer.C
		rc.currentStatus = "Cooking complete. Starting warm mode."
		rc.isCooking = false
		rc.warmMode()
	}()

	rc.isCooking = true
	rc.currentStatus = fmt.Sprintf("Cooking started. Timer set for %d minutes.", rc.timer)
	fmt.Printf("Cooking started. Timer set for %d minutes.\n", rc.timer)
}

func (rc *RiceCooker) warmMode() {
	if rc.quantityOfWater == nil || *rc.quantityOfWater == 0 {
		fmt.Println("Error: Insufficient water. Please add water before starting Warm mode.")
		return
	}
	rc.currentTemperature = 60
	rc.currentStatus = "Warm mode activated. Temperature set to 60°C."
	fmt.Println("Warm mode activated. Temperature set to 60°C.")
}

func (rc *RiceCooker) pauseCooking() {
	if rc.isCooking {
		rc.currentTimer.Stop()
		rc.currentTemperature = 0
		rc.isCooking = false
		rc.currentStatus = "Cooking paused."
		fmt.Println("Cooking paused.")
	} else {
		fmt.Println("No active cooking process to pause.")
	}
}

func (rc *RiceCooker) resumeCooking() {
	if rc.isCooking {
		fmt.Println("Already cooking. Cannot resume.")
	} else {
		rc.currentTimer = time.NewTimer(time.Duration(rc.timer) * time.Minute)

		go func() {
			<-rc.currentTimer.C
			rc.currentStatus = "Cooking complete. Starting warm mode."
			rc.isCooking = false
			rc.warmMode()
		}()

		rc.isCooking = true
		rc.currentTemperature = rc.temperature
		rc.currentStatus = fmt.Sprintf("Cooking resumed. Timer set for %d minutes.", rc.timer)
		fmt.Printf("Cooking resumed. Timer set for %d minutes.\n", rc.timer)
	}
}

func (rc *RiceCooker) checkStatus() {
	fmt.Printf("Status: %s\n", rc.currentStatus)
	fmt.Printf("Temperature: %d °C\n", rc.currentTemperature)
}

func main() {
	cooker := RiceCooker{}

	fmt.Println("Welcome to the Rice Cooker CLI!")

	for {
		fmt.Println("1. Setup Cooking")
		fmt.Println("2. Start Cooking")
		fmt.Println("3. Warm Mode")
		fmt.Println("4. Pause Cooking")
		fmt.Println("5. Resume Cooking")
		fmt.Println("6. Check Status")
		fmt.Println("7. Quit")

		fmt.Print("Enter the number of the command: ")
		var userInput string
		fmt.Scanln(&userInput)

		switch userInput {
		case "1":
			var riceQuantity, waterQuantity, temperature, timer *int
			fmt.Print("Enter quantity of rice (can be null): ")
			fmt.Scanln(&riceQuantity)
			fmt.Print("Enter quantity of water (can be null): ")
			fmt.Scanln(&waterQuantity)
			fmt.Print("Enter temperature (optional, default 100°C): ")
			fmt.Scanln(&temperature)
			fmt.Print("Enter timer (optional, default 30min): ")
			fmt.Scanln(&timer)

			cooker.setupCooking(riceQuantity, waterQuantity, temperature, timer)

		case "2":
			cooker.startCooking()

		case "3":
			cooker.warmMode()

		case "4":
			cooker.pauseCooking()

		case "5":
			cooker.resumeCooking()

		case "6":
			cooker.checkStatus()

		case "7":
			fmt.Println("Exiting Rice Cooker CLI. Goodbye!")
			return

		default:
			fmt.Println("Invalid command. Please enter a valid number.")
		}
	}
}

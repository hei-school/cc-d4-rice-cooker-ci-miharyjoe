package main

import (
	"fmt"
	"log"
	"os"
	"time"
)

type RiceCooker struct {
	quantityOfRice     int
	quantityOfWater    int
	temperature        int
	timer              int
	currentStatus      string
	currentTemperature int
	currentTimer       *time.Timer
	isCooking          bool
}

func (rc *RiceCooker) setupCooking(riceQuantity, waterQuantity, temperature, timer int) {
	if riceQuantity <= 0 || waterQuantity <= 0 {
		logError("Quantity of rice and water must be greater than 0 during setup.")
		return
	}

	rc.quantityOfRice = riceQuantity
	rc.quantityOfWater = waterQuantity

	if temperature <= 0 {
		logError("Temperature must be greater than 0 during setup.")
		return
	}

	rc.temperature = temperature

	if timer <= 0 {
		logError("Timer must be greater than 0 during setup.")
		return
	}

	rc.timer = timer

	rc.currentStatus = "Setup completed. Ready to start cooking."
	fmt.Println("Setup completed. Ready to start cooking.")
}

func (rc *RiceCooker) startCooking() {
	if rc.quantityOfWater <= 0 {
		logError("Insufficient water. Please add water before starting cooking.")
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
	if rc.quantityOfWater <= 0 {
		logError("Insufficient water. Please add water before starting Warm mode.")
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

func logError(message string) {
	logFile, err := os.OpenFile("error_log.txt", os.O_CREATE|os.O_WRONLY|os.O_APPEND, 0666)
	if err != nil {
		fmt.Println("Error opening log file:", err)
		return
	}
	defer logFile.Close()

	log.SetOutput(logFile)
	log.Println("Error:", message)
	fmt.Println("Error:", message)
}

func main() {
	cooker := &RiceCooker{}

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
			var riceQuantity, waterQuantity, temperature, timer int
			fmt.Print("Enter quantity of rice: ")
			_, err := fmt.Scanln(&riceQuantity)
			if err != nil {
				logError("Invalid input. Please enter a valid number.")
				break
			}

			fmt.Print("Enter quantity of water: ")
			_, err = fmt.Scanln(&waterQuantity)
			if err != nil {
				logError("Invalid input. Please enter a valid number.")
				break
			}

			fmt.Print("Enter temperature: ")
			_, err = fmt.Scanln(&temperature)
			if err != nil {
				logError("Invalid input. Please enter a valid number.")
				break
			}

			fmt.Print("Enter timer: ")
			_, err = fmt.Scanln(&timer)
			if err != nil {
				logError("Invalid input. Please enter a valid number.")
				break
			}

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

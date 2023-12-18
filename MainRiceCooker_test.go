package main

import (
	"testing"
)

func TestRiceCooker(t *testing.T) {
	cooker := &RiceCooker{}

	// Test setupCooking
	cooker.setupCooking(2, 4, 100, 10)
	if cooker.quantityOfRice != 2 || cooker.quantityOfWater != 4 || cooker.temperature != 100 || cooker.timer != 10 {
		t.Errorf("setupCooking failed. Expected values not set.")
	}

	// Test startCooking
	cooker.startCooking()
	if !cooker.isCooking || cooker.currentStatus != "Cooking started. Timer set for 10 minutes." {
		t.Errorf("startCooking failed. Cooker not started.")
	}

	// Test warmMode
	cooker.warmMode()
	if cooker.currentTemperature != 60 || cooker.currentStatus != "Warm mode activated. Temperature set to 60°C." {
		t.Errorf("warmMode failed. Warm mode not activated.")
	}

	// Test pauseCooking
	cooker.pauseCooking()
	if cooker.isCooking || cooker.currentStatus != "Cooking paused." {
		t.Errorf("pauseCooking failed. Cooker not paused.")
	}

	// Test resumeCooking
	cooker.resumeCooking()
	if !cooker.isCooking || cooker.currentTemperature != 100 || cooker.currentStatus != "Cooking resumed. Timer set for 10 minutes." {
		t.Errorf("resumeCooking failed. Cooker not resumed.")
	}

	// Test checkStatus
	cooker.checkStatus() // Just check if it runs without errors, as output can vary

	// Test logError
	logError("test error message") // Just check if it runs without errors
}


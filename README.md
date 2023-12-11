# Rice Cooker CLI

A simple command-line interface (CLI) application for a rice cooker implemented in go

## Functionality

1. **Setup Cooking:**

   - Add quantity of rice (can be null).
   - Add quantity of water (can be null).
   - Add temperature (optional, default 100°C).
   - Add timer (optional, default 30 minutes).

2. **Start Cooking:**

   - Will not launch if there is no water.
   - Stops cooking when the timer reaches 0 and launches warm mode.

3. **Warm Mode:**

   - Sets the temperature to 60°C.
   - No timer.

4. **Pause Cooking:**

   - Pauses the timer.
   - Temperature set to 0°C.

5. **Resume Cooking:**
   - Resumes the timer.
   - Temperature set to the value specified in the setup.

## Usage

1. **Setup Cooking:**

   - Enter the quantity of rice (can be null).
   - Enter the quantity of water (can be null).
   - Enter the temperature (optional, default 100°C).
   - Enter the timer (optional, default 30 minutes).

2. **Start Cooking:**

   - Initiates the cooking process.

3. **Warm Mode:**

   - Activated automatically after cooking is complete.

4. **Pause Cooking:**

   - Pauses the cooking process.

5. **Resume Cooking:**

   - Resumes the cooking process.

6. **Check Status:**

   - Displays the current status and temperature.

7. **Quit:**
   - Exits the Rice Cooker CLI.

## Installation

1. **Install Go:**

   Make sure you have Go installed on your system. You can download and install it from the official Go website: [https://golang.org/dl/](https://golang.org/dl/)

2. **Clone the Repository:**

   ```bash
   git clone -b feature/go https://github.com/hei-school/cc-d2-my-rice-cooker-miharyjoe.git

   cd cc-d2-my-rice-cooker-miharyjoe

   go run MainRiceCooker.go
   ```

   - The linter i use [here](https://golangci-lint.run/)

   ```bash
   golangci-lint run
   ```

# Rice Cooker CLI

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/PHq8Kfj_)

A simple command-line interface (CLI) application for a rice cooker implemented in Kotlin, Typescript,
Dart.

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

## Code Branches

You can find the source code for each language in the following branches:

- [Kotlin Branch](https://github.com/hei-school/cc-d2-my-rice-cooker-miharyjoe/tree/feature/kotlin)
- [Typescript Branch](https://github.com/hei-school/cc-d2-my-rice-cooker-miharyjoe/tree/feature/typescript)
- [Dart Branch](https://github.com/hei-school/cc-d2-my-rice-cooker-miharyjoe/tree/feature/dart)
- [GO Branch](https://github.com/hei-school/cc-d2-my-rice-cooker-miharyjoe/tree/feature/go)

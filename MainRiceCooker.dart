import 'dart:async';
import 'dart:io';

class RiceCooker {
  int? quantityOfRice;
  int? quantityOfWater;
  int temperature = 100;
  int timer = 30;
  String currentStatus = 'Idle';
  int currentTemperature = 0;
  Timer? currentTimer;
  bool isCooking = false;

  void setupCooking(
      [int? riceQuantity, int? waterQuantity, int? temperature, int? timer]) {
    this.quantityOfRice = riceQuantity;
    this.quantityOfWater = waterQuantity;
    this.currentTemperature = temperature ?? this.temperature;
    this.timer = timer ?? this.timer;
    currentStatus = 'Setup completed. Ready to start cooking.';
    print('Setup completed. Ready to start cooking.');
  }

  void startCooking() {
    if (quantityOfWater == null || quantityOfWater == 0) {
      print(
          'Error: Insufficient water. Please add water before starting cooking.');
      return;
    }

    currentTimer?.cancel();
    currentTimer = Timer(
      Duration(minutes: timer),
      () {
        currentStatus = 'Cooking complete. Starting warm mode.';
        isCooking = false;
        warmMode();
      },
    );

    isCooking = true;
    currentStatus = 'Cooking started. Timer set for $timer minutes.';
    print('Cooking started. Timer set for $timer minutes.');
  }

  void warmMode() {
    if (quantityOfWater == null || quantityOfWater == 0) {
      print(
          'Error: Insufficient water. Please add water before starting Warm mode.');
      return;
    }
    currentTemperature = 60;
    currentStatus = 'Warm mode activated. Temperature set to 60°C.';
    print('Warm mode activated. Temperature set to 60°C.');
  }

  void pauseCooking() {
    if (isCooking) {
      currentTimer?.cancel();
      currentTemperature = 0;
      isCooking = false;
      currentStatus = 'Cooking paused.';
      print('Cooking paused.');
    } else {
      print('No active cooking process to pause.');
    }
  }

  void resumeCooking() {
    if (isCooking) {
      print('Already cooking. Cannot resume.');
    } else {
      currentTimer = Timer(
        Duration(minutes: timer),
        () {
          currentStatus = 'Cooking complete. Starting warm mode.';
          isCooking = false;
          warmMode();
        },
      );
      isCooking = true;
      currentTemperature = temperature;
      currentStatus = 'Cooking resumed. Timer set for $timer minutes.';
      print('Cooking resumed. Timer set for $timer minutes.');
    }
  }

  void checkStatus() {
    print('Status: $currentStatus');
    print('Temperature: $currentTemperature °C');
  }
}

String? readlnOrNull() {
  try {
    return stdin.readLineSync();
  } catch (e) {
    return null;
  }
}

void main() {
  var cooker = RiceCooker();

  print('Welcome to the Rice Cooker CLI!');

  while (true) {
    print('1. Setup Cooking');
    print('2. Start Cooking');
    print('3. Warm Mode');
    print('4. Pause Cooking');
    print('5. Resume Cooking');
    print('6. Check Status');
    print('7. Quit');

    stdout.write('Enter the number of the command: ');
    var userInput = readlnOrNull();
    switch (userInput) {
      case '1':
        try {
          stdout.write('Enter quantity of rice (can be null): ');
          var riceQuantity = int.tryParse(readlnOrNull() ?? '') ?? 0;

          stdout.write('Enter quantity of water (can be null): ');
          var waterQuantity = int.tryParse(readlnOrNull() ?? '') ?? 0;

          stdout.write('Enter temperature (optional, default 100°C): ');
          var temperature = int.tryParse(readlnOrNull() ?? '') ?? 100;

          stdout.write('Enter timer (optional, default 30min): ');
          var timer = int.tryParse(readlnOrNull() ?? '') ?? 30;

          cooker.setupCooking(riceQuantity, waterQuantity, temperature, timer);
        } catch (e) {
          print('Error: Invalid input. Please enter a valid number.');
        }
        break;
      case '2':
        cooker.startCooking();
        break;
      case '3':
        cooker.warmMode();
        break;
      case '4':
        cooker.pauseCooking();
        break;
      case '5':
        cooker.resumeCooking();
        break;
      case '6':
        cooker.checkStatus();
        break;
      case '7':
        print('Exiting Rice Cooker CLI. Goodbye!');
        return;
      default:
        print('Invalid command. Please enter a valid number.');
    }
  }
}

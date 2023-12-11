import * as readlineSync from 'readline-sync';

class RiceCooker {
  private quantityOfRice: number | null = null;
  private quantityOfWater: number | null = null;
  private temperature: number = 100;
  private timer: number = 30;
  private currentStatus: string = 'Idle';
  private currentTemperature: number = 0;
  private currentTimer: NodeJS.Timeout | null = null;
  private isCooking: boolean = false;

  public setupCooking(
    riceQuantity: number | null,
    waterQuantity: number | null,
    temperature: number | null,
    timer: number | null
  ): void {
    this.quantityOfRice = riceQuantity;
    this.quantityOfWater = waterQuantity;
    this.currentTemperature =
      temperature !== null ? temperature : this.temperature;
    this.timer = timer !== null ? timer : this.timer;
    this.currentStatus = 'Setup completed. Ready to start cooking.';
    console.log('Setup completed. Ready to start cooking.');
  }

  public startCooking(): void {
    if (this.quantityOfWater === null || this.quantityOfWater === 0) {
      console.log(
        'Error: Insufficient water. Please add water before starting cooking.'
      );
      return;
    }

    if (this.currentTimer) {
      clearTimeout(this.currentTimer);
    }

    this.currentTimer = setTimeout(
      () => {
        this.currentStatus = 'Cooking complete. Starting warm mode.';
        this.isCooking = false;
        this.warmMode();
      },
      this.timer * 60 * 1000
    );

    this.isCooking = true;
    this.currentStatus = `Cooking started. Timer set for ${this.timer} minutes.`;
    console.log(`Cooking started. Timer set for ${this.timer} minutes.`);
  }

  public warmMode(): void {
    if (this.quantityOfWater === null || this.quantityOfWater === 0) {
      console.log(
        'Error: Insufficient water. Please add water before starting Warm mode.'
      );
      return;
    }

    this.currentTemperature = 60;
    this.currentStatus = 'Warm mode activated. Temperature set to 60°C.';
    console.log('Warm mode activated. Temperature set to 60°C.');
  }

  public pauseCooking(): void {
    if (this.isCooking) {
      if (this.currentTimer) {
        clearTimeout(this.currentTimer);
      }

      this.currentTemperature = 0;
      this.isCooking = false;
      this.currentStatus = 'Cooking paused.';
      console.log('Cooking paused.');
    } else {
      console.log('No active cooking process to pause.');
    }
  }

  public resumeCooking(): void {
    if (this.isCooking) {
      console.log('Already cooking. Cannot resume.');
    } else {
      this.currentTimer = setTimeout(
        () => {
          this.currentStatus = 'Cooking complete. Starting warm mode.';
          this.isCooking = false;
          this.warmMode();
        },
        this.timer * 60 * 1000
      );

      this.isCooking = true;
      this.currentTemperature = this.temperature;
      this.currentStatus = `Cooking resumed. Timer set for ${this.timer} minutes.`;
      console.log(`Cooking resumed. Timer set for ${this.timer} minutes.`);
    }
  }

  public checkStatus(): void {
    console.log(`Status: ${this.currentStatus}`);
    console.log(`Temperature: ${this.currentTemperature} °C`);
  }
}

function readLine(question: string): string {
  return readlineSync.question(question);
}

function readInt(question: string): number {
  const input = readLine(question);
  const parsedInt = parseInt(input, 10);

  if (isNaN(parsedInt)) {
    throw new Error('Invalid input. Please enter a valid number.');
  }

  return parsedInt;
}

function main(): void {
  const cooker = new RiceCooker();

  console.log('Welcome to the Rice Cooker CLI!');

  while (true) {
    console.log('1. Setup Cooking');
    console.log('2. Start Cooking');
    console.log('3. Warm Mode');
    console.log('4. Pause Cooking');
    console.log('5. Resume Cooking');
    console.log('6. Check Status');
    console.log('7. Quit');

    const userInput = readLine('Enter the number of the command: ');

    try {
      switch (userInput) {
        case '1':
          const riceQuantity = readInt(
            'Enter quantity of rice (can be null): '
          );
          const waterQuantity = readInt(
            'Enter quantity of water (can be null): '
          );
          const temperature = readInt(
            'Enter temperature (optional, default 100°C): '
          );
          const timer = readInt('Enter timer (optional, default 30min): ');

          cooker.setupCooking(riceQuantity, waterQuantity, temperature, timer);
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
          console.log('Exiting Rice Cooker CLI. Goodbye!');
          process.exit(0);
        default:
          console.log('Invalid command. Please enter a valid number.');
      }
    } catch (error) {
      console.log(error.message);
    }
  }
}

main();

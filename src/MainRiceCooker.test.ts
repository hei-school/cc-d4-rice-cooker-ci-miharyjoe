import { RiceCooker } from "./MainRiceCooker";
describe('RiceCooker', () => {
  let cooker: RiceCooker;

  beforeEach(() => {
    cooker = new RiceCooker();
  });

  test('should set up cooking', () => {
    cooker.setupCooking(1, 2, 80, 1);

    expect(cooker['quantityOfRice']).toBe(1);
    expect(cooker['quantityOfWater']).toBe(2);
    expect(cooker['currentTemperature']).toBe(80);
    expect(cooker['timer']).toBe(1);
    expect(cooker['currentStatus']).toBe('Setup completed. Ready to start cooking.');
  });

  test('should start cooking', () => {
    cooker.setupCooking(1, 2, 80, 1);
    cooker.startCooking();

    expect(cooker['isCooking']).toBe(true);
    expect(typeof cooker['currentTimer']).toBe('object');
    expect(cooker['currentStatus']).toContain('Cooking started. Timer set for');
  });

  test('should pause cooking', () => {
    cooker.setupCooking(1, 2, 80, 1);
    cooker.startCooking();
    cooker.pauseCooking();

    expect(cooker['isCooking']).toBe(false);
    expect(cooker['currentTemperature']).toBe(0);
    expect(cooker['currentStatus']).toBe('Cooking paused.');
  });

  test('should resume cooking', () => {
    cooker.setupCooking(1, 2, 80, 1);
    cooker.startCooking();
    const originalTimer = cooker['currentTimer'];
    cooker.pauseCooking();
    cooker.resumeCooking();

    expect(cooker['isCooking']).toBe(true);
    expect(typeof cooker['currentTimer']).toBe('object');
    expect(cooker['currentTimer']).not.toBe(originalTimer);
    expect(cooker['currentStatus']).toContain('Cooking resumed. Timer set for');
  });

  test('should activate warm mode', () => {
    cooker.setupCooking(1, 2, 80, 1);
    cooker.warmMode();

    expect(cooker['currentTemperature']).toBe(60);
    expect(cooker['currentStatus']).toBe('Warm mode activated. Temperature set to 60°C.');
  });

  test('should check status', () => {
    cooker.setupCooking(1, 2, 80, 1);
    const consoleSpy = jest.spyOn(console, 'log');
    cooker.checkStatus();

    expect(consoleSpy).toHaveBeenCalledWith(expect.stringContaining('Status:'));
    expect(consoleSpy).toHaveBeenCalledWith(expect.stringContaining('Temperature:'));
  });
});

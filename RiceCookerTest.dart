import 'package:test/test.dart';
import 'MainRiceCooker.dart';

void main() {
  group('RiceCooker', () {
    RiceCooker? cooker; // Declare as nullable

    setUp(() {
      cooker = RiceCooker();
    });

    test('Setup Cooking', () {
      cooker!.setupCooking(2, 4, 80, 20);

      expect(cooker!.quantityOfRice, equals(2));
      expect(cooker!.quantityOfWater, equals(4));
      expect(cooker!.currentTemperature, equals(80));
      expect(cooker!.timer, equals(20));
      expect(cooker!.currentStatus, equals('Setup completed. Ready to start cooking.'));
    });

    test('Start Cooking', () {
      cooker!.setupCooking(2, 4, 80, 20);
      cooker!.startCooking();

      expect(cooker!.isCooking, isTrue);
      expect(cooker!.currentStatus, contains('Cooking started.'));
    });

    test('Pause Cooking', () {
      cooker!.setupCooking(2, 4, 80, 20);
      cooker!.startCooking();
      cooker!.pauseCooking();

      expect(cooker!.isCooking, isFalse);
      expect(cooker!.currentStatus, equals('Cooking paused.'));
    });

    test('Resume Cooking', () {
      cooker!.setupCooking(2, 4, 80, 20);
      cooker!.startCooking();
      cooker!.pauseCooking();
      cooker!.resumeCooking();

      expect(cooker!.isCooking, isTrue);
      expect(cooker!.currentStatus, contains('Cooking resumed.'));
    });

    test('Warm Mode', () {
      cooker!.setupCooking(2, 4, 80, 20);
      cooker!.warmMode();

      expect(cooker!.currentTemperature, equals(60));
      expect(cooker!.currentStatus, contains('Warm mode activated.'));
    });

    test('Check Status', () {
      cooker!.setupCooking(2, 4, 80, 20);
      cooker!.checkStatus();

      expect(
        cooker!.currentStatus,
        equals('Setup completed. Ready to start cooking.'),
      );
      expect(cooker!.currentTemperature, equals(80));
    });

  });
}

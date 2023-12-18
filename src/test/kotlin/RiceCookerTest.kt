import org.example.RiceCooker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RiceCookerTest{
    @Test
    fun testSetupCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)

        assertEquals(1, riceCooker.quantityOfRice)
        assertEquals(2, riceCooker.quantityOfWater)
        assertEquals(200, riceCooker.currentTemperature)
        assertEquals(10, riceCooker.timer)
        assertEquals("Setup completed. Ready to start cooking.", riceCooker.currentStatus)
    }

    @Test
    fun testStartCookingWithInsufficientWater() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 0, 200, 10)
        riceCooker.startCooking()


        assertEquals("Insufficient water. Please add water before starting cooking.", riceCooker.currentStatus)
    }

    @Test
    fun testStartCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.startCooking()

        assertTrue(riceCooker.isCooking)
        assertEquals("Cooking started. Timer set for 10 minutes.", riceCooker.currentStatus)
    }


    @Test
    fun testWarmMode() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.startCooking() // Start cooking before entering Warm mode
        riceCooker.warmMode()

        assertEquals(60, riceCooker.currentTemperature)
        assertEquals("Warm mode activated. Temperature set to 60°C.", riceCooker.currentStatus)
    }

    @Test
    fun testPauseCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.startCooking()
        riceCooker.pauseCooking()

        assertFalse(riceCooker.isCooking)
        assertEquals(0, riceCooker.currentTemperature)
        assertEquals("Cooking paused.", riceCooker.currentStatus)
    }

    @Test
    fun testPauseCookingWhenNotCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.pauseCooking()

        assertFalse(riceCooker.isCooking)
        assertEquals("No active cooking process to pause.", riceCooker.currentStatus)
    }

    @Test
    fun testResumeCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.startCooking()
        riceCooker.pauseCooking()
        riceCooker.resumeCooking()

        assertTrue(riceCooker.isCooking)
        assertEquals("Cooking resumed. Timer set for 10 minutes.", riceCooker.currentStatus)
    }

    @Test
    fun testResumeCookingWhenAlreadyCooking() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.startCooking()
        riceCooker.resumeCooking()

        assertEquals("Already cooking. Cannot resume.", riceCooker.currentStatus)
    }

    @Test
    fun testCheckStatus() {
        val riceCooker = RiceCooker()
        riceCooker.setupCooking(1, 2, 200, 10)
        riceCooker.checkStatus()

        assertEquals("Setup completed. Ready to start cooking.", riceCooker.currentStatus)
        assertEquals(200, riceCooker.currentTemperature)
    }

}

package org.example

import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class ErrorHandler {
    companion object {
        fun logError(errorMessage: String) {
            val logDirectory = "log"
            val logFilePath = "$logDirectory/error_log.txt"

            // Create the log directory if it doesn't exist
            File(logDirectory).mkdirs()

            // Get the current timestamp for the log entry
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

            // Log the error message with timestamp to a file
            FileWriter(logFilePath, true).use { writer ->
                writer.appendln("$timestamp: $errorMessage")
            }
        }
    }
}

package com.tea505.teaplanner.core.utils

import kotlin.math.PI

/**
 * Utility class for various unit conversions.
 */
object ConversionUtils {

    /** Length Conversions  **/

    fun inchesToCentiMeters(inches: Double): Double {
        return inches * 2.54
    }

    fun centimetersToInches(centimeters: Double): Double {
        return centimeters / 2.54
    }

    fun inchesToFeet(inches: Double): Double {
        return inches / 12
    }

    fun centimetersToFeet(centimeters: Double): Double {
        return centimeters / 30.48
    }

    fun feetToMeters(feet: Double): Double {
        return feet * 0.3048
    }

    fun metersToFeet(meters: Double): Double {
        return meters / 0.3048
    }

    // REMEMBER IF USING A REGULAR SERVO UR RANGE IS 0 TO 180 DEGREES.
    // HOWEVER, A SMART REV SERVO'S RANGE IS 0 to 270 DEGREES.
    // Despite the servo configured the positional range will always be 0 to 1.
    /**
     * Convert degrees to servo position (0 to 1 range)
     */
    fun degreesToServoPosition(degrees: Double): Double {
        return degrees / 180.0
    }

    /**
     * Convert radians to servo position (0 to 1 range)
     */
    fun radiansToServoPosition(radians: Double): Double {
        return radians / PI
    }
}
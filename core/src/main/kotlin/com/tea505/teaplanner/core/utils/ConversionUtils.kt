package com.tea505.teaplanner.core.utils

import kotlin.math.PI

/**
 * Utility class for various unit conversions.
 */
object ConversionUtils {

    /** Length Conversions  **/

    @JvmStatic
    fun inchesToCentiMeters(inches: Double): Double {
        return inches * 2.54
    }

    @JvmStatic
    fun centimetersToInches(centimeters: Double): Double {
        return centimeters / 2.54
    }

    @JvmStatic
    fun inchesToFeet(inches: Double): Double {
        return inches / 12
    }

    @JvmStatic
    fun centimetersToFeet(centimeters: Double): Double {
        return centimeters / 30.48
    }

    @JvmStatic
    fun feetToMeters(feet: Double): Double {
        return feet * 0.3048
    }

    @JvmStatic
    fun metersToFeet(meters: Double): Double {
        return meters / 0.3048
    }

    // REMEMBER IF USING A REGULAR SERVO UR RANGE IS 0 TO 180 DEGREES.
    // HOWEVER, A SMART REV SERVO'S RANGE IS 0 to 270 DEGREES.
    // Despite the servo configured the positional range will always be 0 to 1.
    /**
     * Convert degrees to servo position (0 to 1 range)
     */
    @JvmStatic
    fun degreesToServoPosition(degrees: Double): Double {
        return degrees / 180.0
    }

    /**
     * Convert radians to servo position (0 to 1 range)
     */
    @JvmStatic
    fun radiansToServoPosition(radians: Double): Double {
        return radians / PI
    }

    @JvmStatic
    fun servoPositionToDegrees(pos: Double): Double {
        if (pos < 0 || pos > 1) {
            throw IllegalArgumentException("Normalized position must be between 0 and 1.");
        }
        return 0 + (pos * (180 - 0))
    }

    @JvmStatic
    fun servoPositionToRadians(pos: Double): Double {
        return MathUtils.degreesToRadians(servoPositionToDegrees(pos))
    }

}
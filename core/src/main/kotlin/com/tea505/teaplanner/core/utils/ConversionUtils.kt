package com.tea505.teaplanner.core.utils

/**
 * Utility class for various unit conversions.
 */
object ConversionUtils {

    /** Length Conversions  **/

    @JvmStatic
    fun millimetersToCentimeters(millimeters: Double): Double {
        return millimeters / 10
    }

    @JvmStatic
    fun centimetersToMillimeters(centimeters: Double): Double {
        return centimeters * 10
    }

    @JvmStatic
    fun millimetersToInches(millimeters: Double): Double {
        return millimeters / 25.4
    }

    @JvmStatic
    fun inchesToMillimeters(inches: Double): Double {
        return inches * 25.4
    }

    @JvmStatic
    fun inchesToCentiMeters(inches: Double): Double {
        return inches * 2.54
    }

    @JvmStatic
    fun centimetersToInches(centimeters: Double): Double {
        return centimeters / 2.54
    }

    /**
     * Convert degrees to servo position (0 to 1 range)
     */
    @JvmStatic
    fun degreesToServoPosition(degrees: Double, angleRange: Double): Double {
        return degrees / angleRange
    }

    /**
     * Convert radians to servo position (0 to 1 range)
     */
    @JvmStatic
    fun radiansToServoPosition(radians: Double, angleRange: Double): Double {
        val degrees = MathUtils.radiansToDegrees(radians)
        return degreesToServoPosition(degrees, angleRange)
    }

    @JvmStatic
    fun servoPositionToDegrees(Position: Double, angleRange: Double): Double {
        if (Position < 0 || Position > 1) {
            throw IllegalArgumentException("Position must be between 0 and 1.");
        }
        return Position * angleRange
    }

    @JvmStatic
    fun servoPositionToRadians(Position: Double, angleRange: Double): Double {
        return MathUtils.degreesToRadians(servoPositionToDegrees(Position, angleRange))
    }

}
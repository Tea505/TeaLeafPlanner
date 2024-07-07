package com.tea505.teaplanner.core.utils

import java.lang.Math
import kotlin.math.*

/**
 * Utility class providing mathematical functions.
 */
object MathUtils {

    val PI: Double = 3.141592653589793

    const val EPSILON = 1e-6

    /**
     * Computes the sine of an angle.
     */
    fun sine(radians: Double): Double {
        return sin(radians)
    }

    /**
     * Computes the cosine of an angle.
     */
    fun cosine(radians: Double): Double {
        return cos(radians)
    }

    /**
     * Computes the tangent of an angle.
     */
    fun tangent(radians: Double): Double {
        return tan(radians)
    }

    /**
     * Computes the arcsine of a value.
     */
    fun arcSine(value: Double): Double {
        return asin(value)
    }

    /**
     * Computes the arccosine of a value.
     */
    fun arcCosine(value: Double): Double {
        return acos(value)
    }

    /**
     * Computes the arctangent of a value.
     */
    fun arcTangent(value: Double): Double {
        return atan(value)
    }

    /**
     * Computes the absolute value of a number.
     */
    fun absoluteValue(x: Double): Double {
        return Math.abs(x)
    }

    /**
     * Computes the value of the specified base raised to the specified exponent.
     */
    fun power(base: Double, exponent: Int): Double {
        return base.pow(exponent.toDouble())
    }

    /**
     * Returns the maximum of two Integers and two Doubles.
     */
    fun max(a: Int, b: Int): Int {
        return Math.max(a, b)
    }

    fun max(a: Double, b: Double): Double {
        return Math.max(a, b)
    }

    /**
     * Returns the minimum of two integers and two doubles
     */
    fun min(a: Int, b: Int): Int {
        return Math.min(a, b)
    }

    fun min(a: Double, b: Double): Double {
        return Math.min(a, b)
    }

    /**
     * Returns the signum function of a distance.
     */
    fun signum(distance: Double): Double {
        return Math.signum(distance)
    }

    /**
     * Returns the signum function of a distance.
     */
    fun signum(distance: Float): Float {
        return Math.signum(distance)
    }

    /**
     * Converts an angle measured in degrees to radians.
     */
    fun degreesToRadians(degrees: Double): Double {
        return Math.toRadians(degrees)
    }

    /**
     * Converts an angle measured in radians to degrees.
     */
    fun radiansToDegrees(radians: Double): Double {
        return Math.toDegrees(radians)
    }

    /**
     * Normalizes an angle measured in degrees to the range [0, 360).
     */
    fun normalizeDegrees(degrees: Double): Double {
        var normalized = degrees % 360
        if (normalized < 0) {
            normalized += 360
        }
        return normalized
    }

    /**
     * Normalizes an angle measured in radians to the range [0, 2π).
     */
    fun normalizeRadians(radians: Double): Double {
        var normalized = radians % (2 * Math.PI)
        if (normalized < 0) {
            normalized += 2 * Math.PI
        }
        return normalized
    }

    /**
     * Computes the angular distance between two angles measured in radians.
     */
    fun getRadRotDist(start: Double, end: Double): Double {
        var difference = (end - start + Math.PI) % (2 * Math.PI) - Math.PI
        if (difference < -Math.PI) {
            difference += Math.PI * 2
        }
        return difference
    }

    /**
     * Computes the angular distance between two angles measured in degrees.
     */
    fun getRotDist(start: Double, end: Double): Double {
        return getRadRotDist(start, end)
    }

    /**
     * Computes the factorial of a non-negative integer.
     */
    fun factorial(n: Int): Long {
        if (n < 0) {
            throw IllegalArgumentException("Input must be non-negative.")
        }
        var result: Long = 1
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    /**
     * Computes the square root of a number.
     */
    fun sqrt(x: Double): Double {
        if (x < 0) {
            throw IllegalArgumentException("Cannot calculate square root of a negative number")
        }
        return Math.sqrt(x)
    }

    /**
     * Computes the hypotenuse of a right-angled triangle.
     */
    fun hypot(x: Double, y: Double): Double {
        return Math.hypot(x, y)
    }

    infix fun Double.epsilonEquals(other: Double) = absoluteValue(this - other) < EPSILON

    /**
     * Clip the number if it is less than min or greater than max.
     */
    fun clip(number: Double, min: Double, max: Double): Double {
        if (number < min) return min
        if (number > max) return max
        return number
    }
}
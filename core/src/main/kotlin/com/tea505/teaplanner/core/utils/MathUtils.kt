package com.tea505.teaplanner.core.utils

import java.lang.Math
import kotlin.math.*

/**
 * Utility class providing mathematical functions.
 */
object MathUtils {

    /**
     * Computes the sine of an angle.
     *
     * @param radians the angle in radians
     * @return the sine of the angle
     */
    fun sine(radians: Double): Double {
        return sin(radians)
    }

    /**
     * Computes the cosine of an angle.
     *
     * @param radians the angle in radians
     * @return the cosine of the angle
     */
    fun cosine(radians: Double): Double {
        return cos(radians)
    }

    /**
     * Computes the tangent of an angle.
     *
     * @param radians the angle in radians
     * @return the tangent of the angle
     */
    fun tangent(radians: Double): Double {
        return tan(radians)
    }

    /**
     * Computes the arcsine of a value.
     *
     * @param value the value
     * @return the arcsine of the value
     */
    fun arcSine(value: Double): Double {
        return asin(value)
    }

    /**
     * Computes the arccosine of a value.
     *
     * @param value the value
     * @return the arccosine of the value
     */
    fun arcCosine(value: Double): Double {
        return acos(value)
    }

    /**
     * Computes the arctangent of a value.
     *
     * @param value the value
     * @return the arctangent of the value
     */
    fun arcTangent(value: Double): Double {
        return atan(value)
    }

    /**
     * Computes the absolute value of a number.
     *
     * @param x the number
     * @return the absolute value of the number
     */
    fun absoluteValue(x: Double): Double {
        return Math.abs(x)
    }

    /**
     * Computes the value of the specified base raised to the specified exponent.
     *
     * @param base     the base
     * @param exponent the exponent
     * @return the value of base raised to the exponent
     */
    fun power(base: Double, exponent: Int): Double {
        return base.pow(exponent.toDouble())
    }

    /**
     * Returns the maximum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the maximum of the two integers
     */
    fun max(a: Int, b: Int): Int {
        return Math.max(a, b)
    }

    fun max(a: Double, b: Double): Double {
        return Math.max(a, b)
    }

    /**
     * Returns the minimum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the minimum of the two integers
     */
    fun min(a: Int, b: Int): Int {
        return Math.min(a, b)
    }

    fun min(a: Double, b: Double): Double {
        return Math.min(a, b)
    }

    /**
     * Returns the signum function of a distance.
     *
     * @param distance the distance as a double
     * @return the signum function of the distance
     */
    fun signum(distance: Double): Double {
        return Math.signum(distance)
    }

    /**
     * Returns the signum function of a distance.
     *
     * @param distance the distance as a float
     * @return the signum function of the distance
     */
    fun signum(distance: Float): Float {
        return Math.signum(distance)
    }

    /**
     * Converts an angle measured in degrees to radians.
     *
     * @param degrees the angle in degrees
     * @return the angle in radians
     */
    fun degreesToRadians(degrees: Double): Double {
        return Math.toRadians(degrees)
    }

    /**
     * Converts an angle measured in radians to degrees.
     *
     * @param radians the angle in radians
     * @return the angle in degrees
     */
    fun radiansToDegrees(radians: Double): Double {
        return Math.toDegrees(radians)
    }

    /**
     * Normalizes an angle measured in degrees to the range [0, 360).
     *
     * @param degrees the angle in degrees
     * @return the normalized angle in degrees
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
     *
     * @param radians the angle in radians
     * @return the normalized angle in radians
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
     *
     * @param start the start angle in radians
     * @param end   the end angle in radians
     * @return the angular distance between the angles
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
     *
     * @param start the start angle in degrees
     * @param end   the end angle in degrees
     * @return the angular distance between the angles
     */
    fun getRotDist(start: Double, end: Double): Double {
        return getRadRotDist(start, end)
    }

    /**
     * Computes the factorial of a non-negative integer.
     *
     * @param n the non-negative integer
     * @return the factorial of the integer
     * @throws IllegalArgumentException if the input is negative
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
     *
     * @param x the number
     * @return the square root of the number
     * @throws IllegalArgumentException if the number is negative
     */
    fun sqrt(x: Double): Double {
        if (x < 0) {
            throw IllegalArgumentException("Cannot calculate square root of a negative number")
        }
        return Math.sqrt(x)
    }

    /**
     * Computes the hypotenuse of a right-angled triangle.
     *
     * @param x the length of one side of the triangle
     * @param y the length of the other side of the triangle
     * @return the length of the hypotenuse
     */
    fun hypot(x: Double, y: Double): Double {
        return Math.hypot(x, y)
    }

    const val EPSILON = 1e-6

    infix fun Double.epsilonEquals(other: Double) = absoluteValue(this - other) < EPSILON
}
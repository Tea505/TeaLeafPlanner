package com.tea505.teaplanner.core.geometry

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents a point in 2D space with x and y coordinates.
 */
open class Point(var x: Double, var y: Double) {

    /**
     * Returns the sum of this point and another point.
     */
    fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    /**
     * Returns the difference between this point and another point.
     */
    fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    /**
     * Returns a new point by dividing this point by a scalar value.
     */
    fun div(scalar: Double): Point {
        if (scalar == 0.0) throw IllegalArgumentException("Cannot be divided by Zero")
        return Point(x / scalar, y / scalar)
    }

    /**
     * Returns a new point by multiplying this point by a scalar value.
     */
    open fun mult(scalar: Double): Point {
        return Point(x * scalar, y * scalar)
    }

    /**
     * Computes the arctangent of the point.
     */
    fun atan(): Double {
        return Math.atan2(x, y)
    }

    /**
     * Computes the radius of the point.
     */
    fun radius(): Double {
        return MathUtils.hypot(x, y)
    }

    /**
     * Returns a new point in polar coordinates.
     */
    fun polar(r: Double, a: Double): Point {
        return Point(MathUtils.cosine(a) * r, MathUtils.sine(a) * r)
    }

    /**
     * Rotates the point by a specified amount.
     */
    fun rotate(amount: Double): Point {
        return polar(radius(), atan() + amount)
        //         return polar(radius(), atan() + amount)
    }

    /**
     * Computes the distance between this point and another point.
     */
    fun distanceTo(other: Point): Double {
        return other.minus(this).radius()
    }

    /**
     * Returns a string representation of the point in the format "x, y".
     */
    override fun toString(): String {
        return "$x, $y"
    }
}
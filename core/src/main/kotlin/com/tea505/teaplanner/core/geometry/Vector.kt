package com.tea505.teaplanner.core.geometry

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents a 2D vector with x and y components.
 */
class Vector(var x: Double, var y: Double) {

    /**
     * Returns the sum of this vector and another vector.
     */
    fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    /**
     * Returns the difference between this vector and another vector.
     */
    fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y)
    }

    /**
     * Returns a new vector by dividing this vector by a scalar value.
     */
    fun div(scalar: Double): Vector {
        if (scalar == 0.0) throw IllegalArgumentException("Cannot be divided by Zero")
        return Vector(x / scalar, y / scalar)
    }

    /**
     * Returns a new vector by multiplying this vector by a scalar value.
     */
    fun mult(scalar: Double): Vector {
        return Vector(x * scalar, y * scalar)
    }

    /**
     * Computes the dot product of this vector and another vector.
     */
    fun dot(other: Vector): Double {
        return x * other.x + y * other.y
    }

    /**
     * Computes the magnitude of the vector.
     */
    fun magnitude(): Double {
        return Math.hypot(x, y)
    }

    /**
     * Rotates the vector by a specified angle.
     */
    fun rotate(angle: Double): Vector {
        val cosAngle = MathUtils.cosine(angle)
        val sinAngle = MathUtils.sine(angle)
        return Vector(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle)
    }

    /**
     * Returns the reflection of the vector.
     */
    fun reflect(): Vector {
        return Vector(-x, -y)
    }

    /**
     * Returns the absolute value of the components of the vector.
     */
    fun abs(): Vector {
        return Vector(MathUtils.absoluteValue(x), MathUtils.absoluteValue(y))
    }

    /**
     * Projects this vector onto another vector.
     */
    fun project(other: Vector): Vector {
        val dotProduct = dot(other)
        val magnitudeProduct = magnitude() * other.magnitude()
        return other.mult(dotProduct / magnitudeProduct)
    }

    /**
     * Returns a string representation of the vector in the format "x, y".
     */
    override fun toString(): String {
        return "$x, $y"
    }
}
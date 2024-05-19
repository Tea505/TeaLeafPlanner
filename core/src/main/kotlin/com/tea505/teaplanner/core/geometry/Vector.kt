package com.tea505.teaplanner.core.geometry

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents a 2D vector with x and y components.
 */
class Vector(var x: Double, var y: Double) {

    /**
     * Returns the sum of this vector and another vector.
     *
     * @param other the other vector
     * @return the sum of the vectors
     */
    fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    /**
     * Returns the difference between this vector and another vector.
     *
     * @param other the other vector
     * @return the difference between the vectors
     */
    fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y)
    }

    /**
     * Returns a new vector by dividing this vector by a scalar value.
     *
     * @param scalar the scalar value
     * @return the new vector
     * @throws IllegalArgumentException if the scalar is zero
     */
    fun div(scalar: Double): Vector {
        if (scalar == 0.0) throw IllegalArgumentException("Cannot be divided by Zero")
        return Vector(x / scalar, y / scalar)
    }

    /**
     * Returns a new vector by multiplying this vector by a scalar value.
     *
     * @param scalar the scalar value
     * @return the new vector
     */
    fun mult(scalar: Double): Vector {
        return Vector(x * scalar, y * scalar)
    }

    /**
     * Computes the dot product of this vector and another vector.
     *
     * @param other the other vector
     * @return the dot product
     */
    fun dot(other: Vector): Double {
        return x * other.x + y * other.y
    }

    /**
     * Computes the magnitude of the vector.
     *
     * @return the magnitude
     */
    fun magnitude(): Double {
        return Math.hypot(x, y)
    }

    /**
     * Rotates the vector by a specified angle.
     *
     * @param angle the angle to rotate by
     * @return the rotated vector
     */
    fun rotate(angle: Double): Vector {
        val cosAngle = MathUtils.cosine(angle)
        val sinAngle = MathUtils.sine(angle)
        return Vector(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle)
    }

    /**
     * Returns the reflection of the vector.
     *
     * @return the reflected vector
     */
    fun reflect(): Vector {
        return Vector(-x, -y)
    }

    /**
     * Returns the absolute value of the components of the vector.
     *
     * @return the absolute value vector
     */
    fun abs(): Vector {
        return Vector(MathUtils.absoluteValue(x), MathUtils.absoluteValue(y))
    }

    /**
     * Projects this vector onto another vector.
     *
     * @param other the other vector
     * @return the projected vector
     */
    fun project(other: Vector): Vector {
        val dotProduct = dot(other)
        val magnitudeProduct = magnitude() * other.magnitude()
        return other.mult(dotProduct / magnitudeProduct)
    }

    /**
     * Returns a string representation of the vector in the format "x, y".
     *
     * @return a string representation of the vector
     */
    override fun toString(): String {
        return "$x, $y"
    }
}
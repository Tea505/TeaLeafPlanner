package com.tea505.teaplanner.core.geometry

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents a 2D pose with x, y coordinates and a heading.
 */
open class Pose : Point {

    var heading: Double = 0.0
    var tangent: Vector? = null

    constructor(x: Double, y: Double, heading: Double) : super(x, y) {
        this.heading = MathUtils.normalizeRadians(heading)
    }

    constructor(x: Int, y: Int, heading: Double) : super(x.toDouble(), y.toDouble()) {
        this.heading = MathUtils.normalizeRadians(heading)
    }

    constructor(point: Point, heading: Double) :
            this(point.x, point.y, MathUtils.normalizeRadians(heading))

    constructor(vector2d: Vector, headingTan: Double) : super(vector2d.x, vector2d.y) {
        tangent = Vector(MathUtils.cosine(headingTan), MathUtils.sine(headingTan))
    }

    /**
     * Sets the pose to the values of another pose.
     *
     * @param other the other pose
     */
    fun set(other: Pose) {
        x = other.x
        y = other.y
        heading = other.heading
    }

    /**
     * Returns the sum of this pose and another pose.
     *
     * @param other the other pose
     * @return the sum of the poses
     */
    fun plus(other: Pose): Pose {
        return Pose(x + other.x, y + other.y, heading + other.heading)
    }

    /**
     * Returns the difference between this pose and another pose.
     *
     * @param other the other pose
     * @return the difference between the poses
     */
    fun minus(other: Pose): Pose {
        return Pose(x - other.x, y - other.y,
            MathUtils.normalizeRadians(heading - other.heading))
    }

    /**
     * Returns the division of this pose by another pose.
     *
     * @param other the other pose
     * @return the division of the poses
     */
    fun div(other: Pose): Pose {
        return Pose(x / other.x, y / other.y, heading / other.heading)
    }

    /**
     * Returns a new pose by multiplying this pose by a scalar value.
     *
     * @param scalar the scalar value
     * @return the new pose
     */
    override fun mult(scalar: Double): Pose {
        return Pose(x * scalar, y * scalar, heading * scalar)
    }

    fun toVector2d(): Vector {
        return Vector(x, y)
    }

    /**
     * Returns a string representation of the pose in the format "x, y, heading".
     *
     * @return a string representation of the pose
     */
    override fun toString(): String {
        return "$x, $y, $heading"
    }

}
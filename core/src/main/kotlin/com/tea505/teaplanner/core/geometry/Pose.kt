package com.tea505.teaplanner.core.geometry

import com.tea505.teaplanner.core.utils.MathUtils
import com.tea505.teaplanner.core.utils.MathUtils.epsilonEquals

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

    constructor() : super(0.0, 0.0)

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
        return Pose(x + other.x, y + other.y,
            MathUtils.normalizeRadians(heading + other.heading))
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

    // Convert to Vector heading.
    fun headingVector() = Vector(MathUtils.cosine(heading), MathUtils.sine(heading))

    // Converts to Vector.
    fun toVector2d(): Vector {
        return Vector(x, y)
    }

    /**
     * Compares this Pose object to another Pose object for approximate equality.
     *
     * @param other The Pose object to compare against.
     * @return True if the x, y, and heading properties of both Pose objects are approximately equal, false otherwise.
     */
    fun epsilonEquals(other: Pose) {
        x epsilonEquals other.x && y epsilonEquals other.y && heading epsilonEquals other.heading
    }

    /**
     * Compares this Pose object to another Pose object for approximate equality,
     * with special handling for the heading property to account for angular wrap-around.
     *
     * @param other The Pose object to compare against.
     * @return True if the x, y, and normalized heading properties of both Pose objects are approximately equal, false otherwise.
     */
    fun epsilonEqualsHeading(other: Pose) =
        x epsilonEquals other.x && y epsilonEquals other.y && Angle.normDelta(heading - other.heading) epsilonEquals 0.0

    /**
     * Returns a string representation of the pose in the format "x, y, heading".
     *
     * @return a string representation of the pose
     */
    override fun toString(): String {
        return "$x, $y, $heading"
    }

}
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

    constructor(point: Point) :
            this(point.x, point.y, 0.0)

    constructor() : super(0.0, 0.0)

    /**
     * Sets the pose to the values of another pose.
     */
    fun set(other: Pose) {
        x = other.x
        y = other.y
        heading = other.heading
    }

    /**
     * Returns the sum of this pose and another pose.
     */
    fun plus(other: Pose): Pose {
        return Pose(x + other.x, y + other.y,
            MathUtils.normalizeRadians(heading + other.heading))
    }

    /**
     * Returns the difference between this pose and another pose.
     */
    fun minus(other: Pose): Pose {
        return Pose(x - other.x, y - other.y,
            MathUtils.normalizeRadians(heading - other.heading))
    }

    /**
     * Returns the division of this pose by another pose.
     */
    fun div(other: Pose): Pose {
        return Pose(x / other.x, y / other.y, heading / other.heading)
    }

    /**
     * Returns a new pose by multiplying this pose by a scalar value.
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
     */
    fun epsilonEquals(other: Pose) {
        x epsilonEquals other.x && y epsilonEquals other.y && heading epsilonEquals other.heading
    }

    /**
     * Compares this Pose object to another Pose object for approximate equality,
     * with special handling for the heading property to account for angular wrap-around.
     */
    fun epsilonEqualsHeading(other: Pose) =
        x epsilonEquals other.x && y epsilonEquals other.y && Angle.normDelta(heading - other.heading) epsilonEquals 0.0

    /**
     * Returns a string representation of the pose in the format "x, y, heading".
     */
    override fun toString(): String {
        return "$x, $y, $heading"
    }

}
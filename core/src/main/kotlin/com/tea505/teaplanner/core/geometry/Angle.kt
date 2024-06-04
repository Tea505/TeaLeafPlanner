package com.tea505.teaplanner.core.geometry

import kotlin.math.PI

object Angle {

    private const val TAU = PI * 2

    /**
     * Returns [angle] clamped to `[0, 2pi]`.
     *
     * @param angle angle measure in radians
     */
    @JvmStatic
    fun norm(angle: Double): Double {
        var modifiedAngle = angle % TAU

        modifiedAngle = (modifiedAngle + TAU) % TAU

        return modifiedAngle
    }

    /**
     * Returns [angleDelta] clamped to `[-pi, pi]`.
     *
     * @param angleDelta angle delta in radians
     */
    @JvmStatic
    fun normDelta(angleDelta: Double): Double {
        var modifiedAngleDelta = norm(angleDelta)

        if (modifiedAngleDelta > PI) {
            modifiedAngleDelta -= TAU
        }

        return modifiedAngleDelta
    }

    // REMEMBER IF USING A REGULAR SERVO UR RANGE IS 0 TO 180 DEGREES.
    // HOWEVER, A SMART REV SERVO'S RANGE IS 0 to 270 DEGREES.
    // Despite the servo configured the positional range will always be 0 to 1.
    /**
     * Convert degrees to servo position (0 to 1 range)
     */
    fun degreesToServoPosition(degrees: Double): Double {
        return degrees / 180.0
    }

    /**
     * Convert radians to servo position (0 to 1 range)
     */
    fun radiansToServoPosition(radians: Double): Double {
        return radians / PI
    }
}
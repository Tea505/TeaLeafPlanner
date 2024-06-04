package com.tea505.teaplanner.core.controller

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents an FourBarFeedForward controller.
 *
 * @property kV The velocity gain (compensates for velocity).
 * @property kA The acceleration gain (compensates for acceleration).
 * @property kS The static gain (compensates for static friction).
 * @property kG The gravity gain (compensates for gravitational force).
 */
class FourbarFeedForwardController(
    var kV: Double,
    var kA: Double,
    var kS: Double,
    var kG: Double
) {

    /**
     * Calculates the feedforward output for the arm.
     *
     * @param position The desired position (radians).
     * @param velocity The desired velocity (radians per second).
     * @param acceleration The desired acceleration (radians per second squared).
     * @return The feedforward output.
     */
    fun calculate(position: Double, vel: Double, accel: Double): Double {
        return kS * MathUtils.signum(vel) + kG * MathUtils.cosine(position) + kV * vel + kA * accel
    }
}
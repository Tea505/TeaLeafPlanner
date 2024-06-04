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

    constructor(kS: Double, kG: Double): this(0.0, 0.0, kS, kG)

    /**
     * Calculates the feedforward output for the arm.
     *
     * @param position The desired position (radians).
     * @return The feedforward output.
     */
    fun calculate(position: Double): Double {
        return kS * MathUtils.signum(position) + kG * MathUtils.cosine(position);
    }

    /**
     * Calculates the feedforward output for the arm with velocity and acceleration.
     *
     * @param position The desired position (radians).
     * @param vel The desired velocity.
     * @param accel The desired acceleration.
     * @return The feedforward output.
     */
    fun calculate(position: Double, vel: Double, accel: Double): Double {
        return  kS * MathUtils.signum(vel) + kG * MathUtils.cosine(position) + kV * vel + kA * accel
    }
}
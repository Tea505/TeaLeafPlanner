package com.tea505.teaplanner.core.controller

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents an FeedForward controller.
 *
 * @property kS The static gain (compensates for static friction).
 * @property kG The gravity gain (compensates for gravitational force).
 * @property kV The velocity gain (compensates for velocity).
 * @property kA The acceleration gain (compensates for acceleration).
 */
class FeedForwardController(
    var kS: Double,
    var kG: Double,
    var kV: Double,
    var kA: Double
) {

    var kP: Double = 0.0

    /**
     *  @property kP Proportional gain for position control
     */
    constructor(kS: Double, kG: Double, kV: Double, kA: Double, kP: Double) : this(kS, kG, kV, kA) {
        this.kP = kP
    }

    /**
     * Calculates the feedforward output for the arm with velocity and acceleration.
     *
     * @param theta The desired angle (radians).
     * @param vel The desired velocity.
     * @param accel The desired acceleration.
     * @return The computed feedforward output clamped between 0 and 1.
     */
    fun calculateServoOutput(theta: Double, vel: Double, accel: Double): Double {
        var output: Double =  (kS * MathUtils.signum(theta)) + (kG * MathUtils.cosine(theta)) + (kV * vel) + (kA * accel)

        // clamp servo pos to [0, 1]
        output = MathUtils.max(0.0, Math.min(1.0, output))

        return output
    }

    /**
     * Calculates the feedforward output for a motor, incorporating position error correction.
     *
     * @param vel The desired velocity.
     * @param accel The desired acceleration.
     * @param posError The position error for correction.
     * @return The computed motor feedforward output.
     */
    fun calculateMotorOutput(vel: Double, accel: Double, posError: Double): Double {
        var ffPower: Double =  (kS * Math.signum(vel)) + (kV * vel) + (kA * accel) + kG
        var posCorrection: Double = kP * posError
        return ffPower + posCorrection
    }
}
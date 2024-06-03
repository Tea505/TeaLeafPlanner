package com.tea505.teaplanner.core.controller

import com.tea505.teaplanner.core.utils.MathUtils

/**
 * Represents a PIDF controller.
 *
 * @property kP The proportional gain.
 * @property kI The integral gain.
 * @property kD The derivative gain.
 * @property kF The feed-forward gain.
 * @property setPoint The desired setpoint.
 * @property measuredValue The measured value.
 */
class PIDFController(
    private var kP: Double,
    private var kI: Double,
    private var kD: Double,
    private var kF: Double,
    private var setPoint: Double,
    private var measuredValue: Double
) {

    private var minIntegral = -1.0
    private var maxIntegral = 1.0
    private var errorVal_p: Double = 0.0
    private var errorVal_v: Double = 0.0
    private var totalError: Double = 0.0
    private var prevErrorVal: Double = 0.0
    private var errorTolerance_p = 0.05
    private var errorTolerance_v = Double.POSITIVE_INFINITY
    private var lastTimeStamp: Double = 0.0
    private var period: Double = 0.0

    /**
     * Initializes the PIDF controller with the provided gains and set point.
     */
    init {
        errorVal_p = setPoint - measuredValue
        reset()
    }

    /**
     * Initializes the PIDF controller with the provided gains and zero set point and measured value.
     *
     * @param kp The proportional gain.
     * @param ki The integral gain.
     * @param kd The derivative gain.
     * @param kf The feed-forward gain.
     */
    constructor(kp: Double, ki: Double, kd: Double, kf: Double) : this(kp, ki, kd, kf, 0.0, 0.0)

    /**
     * Resets the controller.
     */
    fun reset() {
        totalError = 0.0
        prevErrorVal = 0.0
        lastTimeStamp = 0.0
    }

    /**
     * Sets the error tolerances for position and velocity.
     *
     * @param positionTolerance Position error tolerance.
     */
    fun setTolerance(positionTolerance: Double) {
        setTolerance(positionTolerance, Double.POSITIVE_INFINITY)
    }

    /**
     * Sets the error tolerances for position and velocity.
     *
     * @param positionTolerance Position error tolerance.
     * @param velocityTolerance Velocity error tolerance.
     */
    fun setTolerance(positionTolerance: Double, velocityTolerance: Double) {
        errorTolerance_p = positionTolerance
        errorTolerance_v = velocityTolerance
    }

    /**
     * Returns the current setpoint of the controller.
     *
     * @return The current setpoint.
     */
    fun getSetPoint(): Double {
        return setPoint
    }

    /**
     * Sets the setpoint for the controller.
     *
     * @param sp The desired setpoint.
     */
    fun setSetPoint(sp: Double) {
        setPoint = sp
        errorVal_p = setPoint - measuredValue
        errorVal_v = (errorVal_p - prevErrorVal) / period
    }

    /**
     * Checks if the controller is at the setpoint within the defined tolerances.
     *
     * @return `true` if the controller is at the setpoint, `false` otherwise.
     */
    fun atSetPoint(): Boolean {
        return MathUtils.absoluteValue(errorVal_p) < errorTolerance_p
                && MathUtils.absoluteValue(errorVal_v) < errorTolerance_v
    }

    /**
     * Calculates the next output of the controller using the current measured value.
     *
     * @return The next output value.
     */
    fun calculate(): Double {
        return calculate(measuredValue)
    }

    /**
     * Calculates the next output of the controller using the provided measured value and setpoint.
     *
     * @param pv The current measurement of the process variable.
     * @param sp The desired setpoint.
     * @return The next output value.
     */
    fun calculate(pv: Double, sp: Double): Double {
        setSetPoint(sp)
        return calculate(pv)
    }

    /**
     * Calculates the next output of the controller using the provided measured value.
     *
     * @param pv The current measurement of the process variable.
     * @return The next output value.
     */
    fun calculate(pv: Double): Double {
        prevErrorVal = errorVal_p
        val currentTimeStamp = System.nanoTime().toDouble() / 1E9
        if (lastTimeStamp == 0.0) lastTimeStamp = currentTimeStamp
        period = currentTimeStamp - lastTimeStamp
        lastTimeStamp = currentTimeStamp
        if (measuredValue == pv) {
            errorVal_p = setPoint - measuredValue
        } else {
            errorVal_p = setPoint - pv
            measuredValue = pv
        }
        if (Math.abs(period) > 1E-6) {
            errorVal_v = (errorVal_p - prevErrorVal) / period
        } else {
            errorVal_v = 0.0
        }
        totalError += period * (setPoint - measuredValue)
        totalError = if (totalError < minIntegral) minIntegral else Math.min(maxIntegral, totalError)
        return kP * errorVal_p + kI * totalError + kD * errorVal_v + kF * setPoint
    }

    /**
     * Sets the PIDF coefficients.
     *
     * @param kp The proportional gain.
     * @param ki The integral gain.
     * @param kd The derivative gain.
     * @param kf The feed-forward gain.
     */
    fun setPIDF(kp: Double, ki: Double, kd: Double, kf: Double) {
        kP = kp
        kI = ki
        kD = kd
        kF = kf
    }

    fun setIntegrationBounds(integralMin: Double, integralMax: Double) {
        minIntegral = integralMin
        maxIntegral = integralMax
    }

    fun clearTotalError() {
        totalError = 0.0
    }

    fun setP(kp: Double) {
        kP = kp
    }

    fun setI(ki: Double) {
        kI = ki
    }

    fun setD(kd: Double) {
        kD = kd
    }

    fun setF(kf: Double) {
        kF = kf
    }

    fun getP(): Double {
        return kP
    }

    fun getI(): Double {
        return kI
    }

    fun getD(): Double {
        return kD
    }

    fun getF(): Double {
        return kF
    }

    fun getPeriod(): Double {
        return period
    }

    fun getCoefficients(): DoubleArray {
        return doubleArrayOf(kP, kI, kD, kF)
    }

    fun getPositionError(): Double {
        return errorVal_p
    }

    fun getTolerance(): DoubleArray {
        return doubleArrayOf(errorTolerance_p, errorTolerance_v)
    }

    fun getVelocityError(): Double {
        return errorVal_v
    }
}
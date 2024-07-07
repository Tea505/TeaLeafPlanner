package com.tea505.teaplanner.core.profile

import com.tea505.teaplanner.core.utils.MathUtils

class MotionProfile(
    var initialPos: Double,
    var finalPos: Double,
    var constants: MotionProfileConstants
) {

    var initPos: Double
    var finalP: Double
    var state = MotionState()
    var distance: Double
    var time1: Double
    var time2: Double
    var time3: Double
    var totalTime: Double = 0.0
    var time1_StopPos: Double
    var time2_StopPos: Double
    var max_Vel: Double
    var originalPos = 0.0
    var flipped: Boolean = false


    init {
        if (finalPos < initialPos) {
            flipped = true
            this.originalPos = initialPos
            var temp: Double = initialPos
            initialPos = finalPos
            finalPos = temp
        }
        this.initPos = initialPos
        this.finalP = finalPos
        this.distance = finalPos - initialPos

        this.time1 = constants.velo / constants.accel
        this.time3 = constants.velo / constants.decel

        this.time2 = MathUtils.absoluteValue(distance) / constants.velo - (time1 + time3) / 2

        if (time2 < 0) {
            this.time2 = 0.0

            // this math utilizes a negative deceleration constant. either negate from the passed in value,
            // or just add a negatation symbol prior to the variable.
            var a = (constants.accel / 2) * (1 - constants.accel / -constants.decel)
            var c = -distance

            // acceleration phase
            time1 = MathUtils.sqrt(-4 * a * c) / (2 * a)

            // deceleration phase
            time3 = -(constants.accel * time1) / -constants.decel

            // ending accel position (middle peak)
            time1_StopPos = (constants.accel * MathUtils.power(time1, 2)) / 2

            // ending accel velocity (middle peak)
            max_Vel = constants.accel * time1

            time2_StopPos = time1_StopPos

        } else  {
            // time constants already calculated

            max_Vel = constants.velo
            time1_StopPos = (constants.velo * time1) / 2
            time2_StopPos = time1_StopPos + time2 * max_Vel

        }

        totalTime = time1 + time2 + time3
    }


    fun calculate(time: Double): MotionState {
        var position: Double
        var velocity: Double
        var acceleration: Double
        var stage_Time: Double

        if (time <= time1) {
            stage_Time = time
            acceleration = constants.accel
            velocity = acceleration * stage_Time
            position = velocity * stage_Time / 2

        } else if (time <= time1 + time2) {
            stage_Time = time - time1
            acceleration = 0.0
            velocity = constants.velo
            position = time1_StopPos + stage_Time * velocity

        } else if (time <= totalTime) {
            stage_Time = time - time1 - time2
            acceleration = -constants.decel
            velocity = max_Vel - stage_Time * constants.decel
            position = time2_StopPos + (max_Vel + velocity) / 2 * stage_Time

        } else {

            acceleration = 0.0
            velocity = 0.0
            position = finalPos
        }

        if (flipped) {
            state.xPos = originalPos - position
        } else  {
            state.xPos = initialPos + position
        }

        state.velo = velocity
        state.accel = acceleration

        return this.state
    }
}
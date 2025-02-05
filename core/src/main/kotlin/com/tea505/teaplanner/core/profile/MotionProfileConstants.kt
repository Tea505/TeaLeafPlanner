package com.tea505.teaplanner.core.profile

import com.tea505.teaplanner.core.utils.MathUtils

class MotionProfileConstants (
    velocity: Double,
    acceleration: Double,
    deceleration: Double
) {

    var velo: Double = MathUtils.absoluteValue(velocity)
    var accel: Double = MathUtils.absoluteValue(acceleration)
    var decel: Double = MathUtils.absoluteValue(deceleration)

    fun convert(factor: Double) {
        velo *= factor
        accel *= factor
        decel *= factor
    }

}
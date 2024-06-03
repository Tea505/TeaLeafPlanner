package com.tea505.teaplanner.core.profile

import com.tea505.teaplanner.core.utils.MathUtils

class MotionProfileConstants @JvmOverloads constructor(
    velocity: Double,
    acceleration: Double,
    deceleration: Double
) {

    var velo: Double = MathUtils.absoluteValue(velocity)
        private set

    var accel: Double = MathUtils.absoluteValue(acceleration)
        private set

    var decel: Double = MathUtils.absoluteValue(deceleration)
        private set

    fun convert(factor: Double) {
        velo *= factor
        accel *= factor
        decel *= factor
    }

}
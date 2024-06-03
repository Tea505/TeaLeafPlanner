package com.tea505.teaplanner.core.profile

data class MotionState(
    var xPos: Double = 0.0,
    var velo: Double = 0.0,
    var accel: Double = 0.0
) {

    constructor() : this(0.0, 0.0, 0.0)

}
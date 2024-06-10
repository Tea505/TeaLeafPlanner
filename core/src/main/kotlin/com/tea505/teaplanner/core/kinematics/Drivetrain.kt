package com.tea505.teaplanner.core.kinematics

import com.tea505.teaplanner.core.geometry.Pose

interface Drivetrain {

    fun set(pose: Pose)
}
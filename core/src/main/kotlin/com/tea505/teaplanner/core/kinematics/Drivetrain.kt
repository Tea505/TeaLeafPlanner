package com.tea505.teaplanner.core.kinematics

import com.tea505.teaplanner.core.geometry.Pose

// A Drivetrain interface
interface Drivetrain {

    fun set(pose: Pose)
}
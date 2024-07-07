package com.tea505.teaplanner.core.kinematics

import com.tea505.teaplanner.core.geometry.Pose
import com.tea505.teaplanner.core.localization.Localizer

interface Drivetrain {

    var localizer: Localizer

    fun set(pose: Pose)
}
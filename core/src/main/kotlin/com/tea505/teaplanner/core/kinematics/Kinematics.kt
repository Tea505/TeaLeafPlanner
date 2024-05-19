package com.tea505.teaplanner.core.kinematics

import com.tea505.teaplanner.core.geometry.Angle
import com.tea505.teaplanner.core.geometry.Pose
import com.tea505.teaplanner.core.geometry.Vector
import com.tea505.teaplanner.core.utils.MathUtils
import com.tea505.teaplanner.core.utils.MathUtils.epsilonEquals

object Kinematics {

    /**
     * Performs a relative odometry update. Note: this assumes that the robot moves with constant velocity over the
     * measurement interval.
     */
    @JvmStatic
    fun relativeOdometryUpdate(fieldPose: Pose, robotPoseDelta: Pose): Pose {
        val dtheta = robotPoseDelta.heading
        val (sineTerm, cosTerm) = if (dtheta epsilonEquals 0.0) {
            1.0 - dtheta * dtheta / 6.0 to dtheta / 2.0
        } else {
            MathUtils.sine(dtheta) / dtheta to (1 - MathUtils.cosine(dtheta)) / dtheta
        }

        val fieldPositionDelta = Vector(
            sineTerm * robotPoseDelta.x - cosTerm * robotPoseDelta.y,
            cosTerm * robotPoseDelta.x + sineTerm * robotPoseDelta.y
        )

        val fieldPoseDelta = Pose(fieldPositionDelta.rotate(fieldPose.heading), robotPoseDelta.heading)

        return Pose(
            fieldPose.x + fieldPoseDelta.x,
            fieldPose.y + fieldPoseDelta.y,
            Angle.norm(fieldPose.heading + fieldPoseDelta.heading)
        )
    }
}
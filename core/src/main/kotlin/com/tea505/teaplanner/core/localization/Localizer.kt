package com.tea505.teaplanner.core.localization

import com.tea505.teaplanner.core.geometry.Pose

/**
 * Interface for robot localization.
 */
interface Localizer {

    /**
     * The estimated pose of the robot.
     */
    var poseEstimate: Pose

    /**
     * Sets the estimated pose of the robot.
     *
     * @param pose the estimated pose
     */
    // Apparently this gives an error in logs.
    // fun setPoseEstimate(pose: Pose)

    /**
     * Returns the positions of the tracking wheels.
     *
     * @return the positions of the tracking wheels
     */
    fun getWheelPositions(): List<Double>

    /**
     * Updates the localization based on sensor inputs.
     */
    fun update()
}
package com.tea505.teaplanner.core.event

import com.tea505.teaplanner.core.controller.PIDFController
import com.tea505.teaplanner.core.geometry.Pose
import com.tea505.teaplanner.core.kinematics.Drivetrain
import com.tea505.teaplanner.core.utils.ClockedTimer
import com.tea505.teaplanner.core.utils.MathUtils

// STILL WORK IN PROGRESS
/**
 * Class representing a Positional Action which moves the drivetrain to the desired pose.
 */
open class PoseAction(pose: Pose, drivetrain: Drivetrain?): Action {

    final override var hasPerformed: Boolean = false

    var targetPose = Pose()
    var drivetrain: Drivetrain? = null

    var xP: Double = 0.0
    var xD: Double = 0.0

    var yP: Double = 0.0
    var yD: Double = 0.0

    var hP: Double = 0.0
    var hD: Double = 0.0

    var maxTranslationalSpeed: Double = 1.0
    var maxRotationalSpeed: Double = 1.0

    var headingError: Double = 0.05
    var translationError: Double = 1.0

    var xGain = 0.0
    var yGain = 0.0

    var xController = PIDFController(xP, 0.0, xD, 0.0)
    var yController = PIDFController(yP, 0.0, yD, 0.0)
    var hController = PIDFController(hP, 0.0, hD, 0.0)

    var timer = ClockedTimer()
    var stable = ClockedTimer()

    init {
        if (drivetrain!!.localizer == null) {
            throw IllegalStateException("Localizer is not set")
        }

        this.drivetrain = drivetrain
        this.hasPerformed = false

        targetPose = pose

        xController.reset()
        yController.reset()
        hController.reset()
    }

    override fun perform() {
        if (timer == null) timer = ClockedTimer()
        if (stable == null) stable = ClockedTimer()

        var robotPose = drivetrain!!.localizer.getPose() // TODO: make localizer getPose here

        var powers = getPositionalPower(robotPose)
        drivetrain?.set(powers)

    }

    fun hasPerformed(): Boolean {
        var robotPose = drivetrain!!.localizer.getPose() // TODO: make localizer getPose here too!!
        var delta: Pose = targetPose.minus(robotPose)

        if (delta.toVector2d().magnitude() > translationError
            || MathUtils.absoluteValue(delta.heading) > headingError) {
            stable.reset()
        }

        return timer.currentTimeMilliSeconds() > 2500 || stable.currentTimeMilliSeconds() > 100
    }


    fun getPositionalPower(robotPose: Pose): Pose {
        if (targetPose.heading - robotPose.heading > MathUtils.PI) {
            targetPose.heading -= 2 * MathUtils.PI
        }
        if (targetPose.heading - robotPose.heading < -MathUtils.PI) {
            targetPose.heading += 2 * MathUtils.PI
        }

        var xPower: Double = xController.calculate(robotPose.x, targetPose.x)
        var yPower: Double = yController.calculate(robotPose.y, targetPose.y)
        var hPower: Double = hController.calculate(robotPose.heading, targetPose.heading)

        var xRotated: Double = xPower * MathUtils.cosine(-robotPose.heading) - yPower * MathUtils.sine(-robotPose.heading)
        var yRotated: Double = xPower * MathUtils.sine(-robotPose.heading) + yPower + MathUtils.cosine(-robotPose.heading)

        hPower = MathUtils.clip(hPower, -maxRotationalSpeed, maxRotationalSpeed)
        xRotated = MathUtils.clip(xRotated, -maxTranslationalSpeed / xGain, maxTranslationalSpeed / xGain)
        yRotated = MathUtils.clip(yRotated, -maxTranslationalSpeed / yGain, maxTranslationalSpeed / yGain)

        return Pose(xRotated * xGain, yRotated * yGain, hPower)
    }

    fun performed(interrupted: Boolean) {
        if (interrupted) {
            drivetrain?.set(Pose())
        }
    }
}
package com.tea505.teaplanner.core.localization

import com.tea505.teaplanner.core.geometry.Pose
import com.tea505.teaplanner.core.kinematics.Kinematics
import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.DecompositionSolver
import org.apache.commons.math3.linear.LUDecomposition
import org.apache.commons.math3.linear.MatrixUtils

abstract class ThreeWheelLocalizer(
    wheelPoses: List<Pose>
)  : Localizer {

    private var _poseEstimate = Pose()
    override var poseEstimate: Pose
        get() = _poseEstimate
        set(value) {
            _poseEstimate = value
        }

    var poseVelocity: Pose? = null
    private var lastWheelPositions = emptyList<Double>()

    private val forwardSolver: DecompositionSolver

    init {
        require(wheelPoses.size == 3) { "3 Wheel positions must be provided"}

        val inverseMatrix = Array2DRowRealMatrix(3, 3)
        for (i in 0..2) {
            val orientationVector = wheelPoses[i].headingVector()
            val positionVector = wheelPoses[i].toVector2d()
            inverseMatrix.setEntry(i, 0, orientationVector.x)
            inverseMatrix.setEntry(i, 1, orientationVector.y)
            inverseMatrix.setEntry(
                i,
                2,
                positionVector.x * orientationVector.y - positionVector.y * orientationVector.x
            )
        }

        forwardSolver = LUDecomposition(inverseMatrix).solver

        require(forwardSolver.isNonSingular) { "The specified configuration cannot support full localization" }
    }

    private fun calculatePoseDelta(wheelDeltas: List<Double>): Pose {
        val rawPoseDelta = forwardSolver.solve(
            MatrixUtils.createRealMatrix(
                arrayOf(wheelDeltas.toDoubleArray())
            ).transpose()
        )
        return Pose(
            rawPoseDelta.getEntry(0, 0),
            rawPoseDelta.getEntry(1, 0),
            rawPoseDelta.getEntry(2, 0)
        )
    }

    override fun update() {
        val wheelPositions = getWheelPositions()
        if (lastWheelPositions.isNotEmpty()) {
            val wheelDeltas = wheelPositions
                .zip(lastWheelPositions)
                .map { it.first - it.second }
            val robotPoseDelta = calculatePoseDelta(wheelDeltas)
            _poseEstimate = Kinematics.relativeOdometryUpdate(_poseEstimate, robotPoseDelta)
        }

        val wheelVelocities = getWheelVelocities()
        if (wheelVelocities != null) {
            poseVelocity = calculatePoseDelta(wheelVelocities)
        }

        lastWheelPositions = wheelPositions
    }

    /**
     * Returns the positions of the tracking wheels in the desired distance units (not encoder counts!)
     */
    abstract override fun getWheelPositions(): List<Double>

    /**
     * Returns the velocities of the tracking wheels in the desired distance units (not encoder counts!)
     */
    open fun getWheelVelocities(): List<Double>? = null
}
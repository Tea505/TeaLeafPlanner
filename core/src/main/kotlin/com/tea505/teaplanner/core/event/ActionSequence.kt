package com.tea505.teaplanner.core.event

import com.tea505.teaplanner.core.geometry.Point
import com.tea505.teaplanner.core.geometry.Pose
import com.tea505.teaplanner.core.kinematics.Drivetrain

/**
 * Class representing a sequence of actions to be performed.
 */
class ActionSequence() {

    private var drivetrain: Drivetrain? = null
    private var hasPerformed: Boolean
    private var actionList = ArrayList<ActionBase>()
    private var actionRunner: Runnable? = null
    private var actionThreading: Thread? = null

    constructor(drivetrain: Drivetrain) : this() {
        this.drivetrain = drivetrain
    }

    /**
     * Initializes the action sequence.
     */
    init {
        hasPerformed = true
    }

    /**
     * Adds an action to the sequence.
     *
     * @param action the action to add
     * @return the action sequence
     */
    fun addAction(action: Action): ActionSequence {
        val actionBase = ActionBase(action)
        actionList.add(actionBase)
        return this
    }

    /**
     * Adds a wait action to the sequence.
     *
     * @param milliseconds the duration to wait in milliseconds
     * @return the action sequence
     */
    fun addWaitAction(milliseconds: Double): ActionSequence {
        val waitAction = WaitAction(milliseconds)
        val actionBase = ActionBase(waitAction)
        actionList.add(actionBase)
        return this
    }

    /**
     * Adds an action to set the drivetrain to the given pose.
     *
     * @param position The pose to set the drivetrain to.
     * @return the action sequence.
     */
    fun addPoseAction(position: Any): ActionSequence {

        if (drivetrain == null) {
            throw IllegalStateException("Drivetrain must be initialized before adding a pose action.")
        }

        val poseAction = object : Action {
            override fun perform() {
                when (position) {
                    is Pose -> drivetrain?.set(position)
                    is Point -> {
                        // TODO: Add relative Odom update in here.
                        val pose = Pose(position) // Heading is set to 0.0
                        drivetrain?.set(pose)
                    }

                    else -> throw IllegalArgumentException("Invalid argument type. Expected Pose or Point.")
                }
            }
        }
        val actionBase = ActionBase(poseAction)
        actionList.add(actionBase)
        return this
    }

    /**
     * Builds the action sequence.
     *
     * @return the action sequence
     */
    fun build(): ActionSequence {
        actionRunner = Runnable {
            for (action in actionList) {
                action.perform()
                while (!action.hasPerformed) { }
            }
            hasPerformed = true
        }
        actionThreading = Thread(actionRunner)
        return this
    }

    /**
     * Performs the action sequence.
     */
    fun perform() {
        hasPerformed = false
        actionThreading = Thread(actionRunner)
        actionThreading?.start()
    }

    /**
     * Triggers the action sequence if it has not been performed.
     */
    fun trigger() {
        if (hasPerformed) {
            perform()
        }
    }

    /**
     * Return if the sequence has performed.
     */
    fun hasPerformed(): Boolean {
        return hasPerformed
    }

}
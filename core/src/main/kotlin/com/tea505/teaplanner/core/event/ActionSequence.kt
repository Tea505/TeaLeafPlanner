package com.tea505.teaplanner.core.event

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
    //private var poseAction = PoseAction(Pose(), drivetrain)

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

    /**
     *  Sets the PIDF values for the PIDF Controllers in PoseAction.
     *
     *  @param xP the proportional term of the x Controller
     *  @param xD the derivative term of the x Controller
     *  @param yP the proportional term of the y Controller
     *  @param yD the derivative term of the y Controller
     *  @param hP the proportional term of the h Controller
     *  @param hD the derivative term of the h Controller
     */
    fun setDrivePID(xP: Double, xD: Double, yP: Double, yD: Double, hP: Double, hD: Double): ActionSequence {
        poseAction.xP = xP
        poseAction.xD = xD

        poseAction.yP = yP
        poseAction.yD = yD

        poseAction.hP = hP
        poseAction.hD = hD

        return this
    }

    /**
     *  Sets the allowed Error for the drivetrain.
     *
     *  @param translationalError the translationError in the x and y-axis
     *  @param headingError the headingError of the robot
     */
    fun setDriveError(translationalError: Double, headingError: Double): ActionSequence {
        poseAction.translationError = translationalError
        poseAction.headingError = headingError
        return this
    }

    /**
     * Sets the Maximum Drive speed for the drivetrain.
     *
     * @param translateSpeed the maximum speed for strafing or translating
     * @param rotationalSpeed the maximum speed for rotating
     */
    fun setDriveSpeed(translateSpeed: Double, rotationalSpeed: Double) : ActionSequence {
        poseAction.maxTranslationalSpeed = translateSpeed
        poseAction.maxRotationalSpeed = rotationalSpeed
        return this
    }

    /**
     * Sets the X and Y gains for the position.
     *
     * @param xGain the x gain.
     * @param yGain the y gain.
     */
    fun setGains(xGain: Double, yGain: Double): ActionSequence {
        poseAction.xGain = xGain
        poseAction.yGain = yGain
        return this
    }

    **/

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
    /**
     * Adds an action to set the drivetrain to the given pose.
     *
     * @param pose The pose to set the drivetrain to.
     * @return the action sequence.
     */
    fun addPoseAction(pose: Pose): ActionSequence {
        if (drivetrain == null) {
            throw IllegalStateException("Drivetrain must be initialized before adding a pose action.")
        }

        poseAction = PoseAction(pose, drivetrain!!)

        val actionBase = ActionBase(poseAction)
        actionList.add(actionBase)
        return this
    }
    **/

    /**
     * Builds the action sequence.
     *
     * @return the action sequence
     */
    fun build(): ActionSequence {
        actionRunner = Runnable {
            for (action in actionList) {
                action.perform()
               // while (!action.hasPerformed) { }
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
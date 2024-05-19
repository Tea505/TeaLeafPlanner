package com.tea505.teaplanner.core.event

/**
 * Base class for actions that can be performed.
 *
 * @property action the action to be performed
 */
class ActionBase(var action: Action): Action {

    var hasPerformed = false

    /**
     * Performs the action and sets the hasPerformed flag to true.
     */
    override fun perform() {
        action.perform()
        hasPerformed = true
    }

}
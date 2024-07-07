package com.tea505.teaplanner.core.event

/**
 * Base class for actions that can be performed.
 */
class ActionBase(var action: Action): Action {

    override var hasPerformed = false

    /**
     * Performs the action and sets the hasPerformed flag to true.
     */
    override fun perform() {
        action.perform()
        hasPerformed = true
    }

}
package com.tea505.teaplanner.core.event

import com.tea505.teaplanner.core.utils.ClockedTimer

/**
 * Class representing a wait action.
 *
 * @property waitMilliseconds the duration to wait in milliseconds
 */
class WaitAction(val milliseconds: Double): Action {

    override var hasPerformed: Boolean = false

    // Timer
    private val timer = ClockedTimer()

    init {
        hasPerformed = false
        timer.reset()
    }

    /**
     * Performs the wait action.
     */
    override fun perform() {
        while (timer.currentTimeMilliSeconds() < milliseconds) {
            hasPerformed = false
        }
        hasPerformed = true
    }
}
package com.tea505.teaplanner.core.utils

/**
 * Simple timer class for measuring elapsed time in seconds or milliseconds.
 */
class ClockedTimer {

    /**
     * Previous time recorded by the timer.
     */
    var prevTime: Long = 0

    init {
        reset()
    }

    /**
     * Resets the timer by updating the previous time to the current time.
     */
    fun reset() {
        this.prevTime = System.currentTimeMillis()
    }

    /**
     * Returns the current time in seconds since the epoch.
     */
    fun currentTimeSeconds(): Double {
        return currentTimeMilliSeconds() / 1000.0
    }

    /**
     * Returns the current time in milliseconds.
     */
    fun currentTimeMilliSeconds(): Long {
        return System.currentTimeMillis() - prevTime
    }
}
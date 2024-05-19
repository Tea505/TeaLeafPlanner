package com.tea505.teaplanner.core.utils

/**
 * Simple timer class for measuring elapsed time in seconds.
 */
class ClockedTimer {

    /**
     * Previous time recorded by the timer.
     */
    var prevTime: Double

    /**
     * Initializes the timer with the current time.
     */
    init {
        this.prevTime = getSeconds()
    }

    /**
     * Returns the elapsed time since the last reset or initialization.
     *
     * @return the elapsed time in seconds
     */
    fun currentTime(): Double {
        return getSeconds() - prevTime
    }

    /**
     * Resets the timer by updating the previous time to the current time.
     */
    fun reset() {
        this.prevTime = currentTime()
    }

    /**
     * Returns the current time in seconds since the epoch.
     *
     * @return the current time in seconds
     */
    fun getSeconds(): Double {
        return System.currentTimeMillis() / 1000.0
    }
}
package com.tea505.teaplanner.core.event

/**
 * Interface representing an action to be performed.
 */
interface Action {

    var hasPerformed: Boolean

    fun perform()
}
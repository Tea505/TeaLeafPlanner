package com.tea505.teaplanner.core.geometry

/**
 * Represents a waypoint with a type, point, and radius.
 */
class Waypoint (
    val point: Point,
    val radius: Double
) {
    /** The type of the waypoint. */
    val type: WaypointType = if (point is Pose) WaypointType.POSE else WaypointType.POINT
}
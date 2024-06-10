package com.tea505.teaplanner.core.geometry

/**
 * Represents a waypoint with a type, point, and radius.
 */
class Waypoint (
    val point: Point,
    val radius: Double
) {

    /** The type of the waypoint. */
    var type: WaypointType = if (point is Pose) WaypointType.POSE else WaypointType.POINT

}

/**
 * Enum representing the types of waypoints.
 */
enum class WaypointType {

    /** Indicates a point waypoint. */
    POINT,

    /** Indicates a pose waypoint. */
    POSE

}
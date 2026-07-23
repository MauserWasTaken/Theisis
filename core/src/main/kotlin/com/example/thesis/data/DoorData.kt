package com.example.thesis.data

data class DoorData(
    val id: Int,
    val x: Int,
    val y: Int,

    val direction:DoorDirection = DoorDirection.NORTH,

    var connectedLevel: Int = -1,
    var connectedDoor: Int = -1
)


enum class DoorDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST
}

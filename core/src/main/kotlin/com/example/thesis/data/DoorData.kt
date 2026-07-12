package com.example.thesis.data

data class DoorData(
    val id: Int,
    val x: Int,
    val y: Int,

    var connectedLevel: Int = -1,
    var connectedDoor: Int = -1
)

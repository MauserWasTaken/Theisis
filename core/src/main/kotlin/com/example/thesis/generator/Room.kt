package com.example.thesis.generator

data class Room(

    val id: Int,

    val x: Int,
    val y: Int,

    val width: Int,
    val height: Int
) {
    val centerX
        get() = x + width / 2

    val centerY
        get() = y + height / 2

    val left
        get() = x

    val right
        get() = x + width - 1

    val bottom
        get() = y

    val top
        get() = y + height - 1

    fun intersects(other: Room): Boolean {

        return x < other.x + other.width &&
            x + width > other.x &&
            y < other.y + other.height &&
            y + height > other.y
    }
}
